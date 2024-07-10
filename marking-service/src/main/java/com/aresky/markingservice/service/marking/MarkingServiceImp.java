package com.aresky.markingservice.service.marking;

import com.aresky.markingservice.dto.request.MarkedTourRequest;
import com.aresky.markingservice.dto.response.MarkedTourResponse;
import com.aresky.markingservice.entity.MarkedTour;
import com.aresky.markingservice.exception.MarkingException;
import com.aresky.markingservice.exception.MessageResponse;
import com.aresky.markingservice.repository.IMarkedTourRepository;
import com.aresky.markingservice.service.account.IAccountService;
import com.aresky.markingservice.service.tour.ITourService;
import grpc.tour.dto.response.SubTourResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class MarkingServiceImp implements IMarkingService {
    @Autowired
    private IMarkedTourRepository markedTourRepository;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private ITourService tourService;

    @Transactional
    @Override
    public Mono<Void> markTour(MarkedTourRequest request) {
        Integer accountId = request.getAccountId();
        Integer subTourId = request.getSubTourId();

        return Mono.zip(checkAccountBy(accountId), checkSubTourBy(subTourId))
                .flatMap(tuple -> {
                    SubTourResponse subTour = tuple.getT2();
                    Integer tourId = subTour.getTourId();

                    return isMarkedTourBy(accountId, subTourId)
                            .filter(Boolean.FALSE::equals)
                            .switchIfEmpty(Mono.error(new MarkingException(MessageResponse.MARKED_THIS_TOUR)))
                            .then(markedTourRepository.save(new MarkedTour(accountId, subTourId, tourId)))
                            .then();
                });
    }

    @Transactional
    @Override
    public Mono<Void> unmarkTour(Integer markedTourId) {
        return markedTourRepository.existsById(markedTourId)
                .flatMap(isMarked -> {
                    if (!isMarked) {
                        return Mono.empty();
                    }

                    return markedTourRepository.deleteById(markedTourId);
                });
    }

    @Override
    public Mono<Boolean> checkMarkedTour(Integer accountId, Integer subTourId) {
        return isMarkedTourBy(accountId, subTourId);
    }

    @Override
    public Mono<Void> unmarkTour(Integer accountId, Integer subTourId) {
        return Mono.zip(checkAccountBy(accountId), checkSubTourBy(subTourId))
                .flatMap(tuple -> {
                    return isMarkedTourBy(accountId, subTourId)
                            .filter(Boolean.TRUE::equals)
                            .switchIfEmpty(Mono.error(new MarkingException(MessageResponse.NOT_MARKED_THIS_TOUR)))
                            .then(markedTourRepository.deleteByAccountIdAndSubTourId(accountId, subTourId))
                            .then();
                });
    }

    @Override
    public Mono<List<MarkedTourResponse>> getAllMarkedTourResponses(Integer accountId) {
        return checkAccountBy(accountId)
                .flatMap(existsAccount -> markedTourRepository.findByAccountId(accountId)
                        .flatMap(markedTour -> {
                            MarkedTourResponse dto = MarkedTourResponse.toDto(markedTour);
                            return tourService.getSubTourById(markedTour.getSubTourId())
                                    .map(MarkedTourResponse.SubTour::fromGrpcSubTourResponse)
                                    .map(dto::subTour);
                        })
                        .collectList());
    }

    private Mono<Boolean> isMarkedTourBy(Integer accountId, Integer subTourId) {
        return markedTourRepository.existsByAccountIdAndSubTourId(accountId, subTourId);
    }

    private Mono<Boolean> checkAccountBy(Integer accountId) {
        return accountService.checkExistsAccountById(accountId)
                .filter(Boolean.TRUE::equals)
                .switchIfEmpty(Mono.error(new MarkingException(MessageResponse.INVALID_ACCOUNT_ID)));
    }

    private Mono<SubTourResponse> checkSubTourBy(Integer subTourId) {
        return tourService.getSubTourById(subTourId)
                .switchIfEmpty(Mono.error(new MarkingException(MessageResponse.INVALID_SUB_TOUR_ID)));
    }
}
