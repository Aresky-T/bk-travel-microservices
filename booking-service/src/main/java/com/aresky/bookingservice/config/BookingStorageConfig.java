package com.aresky.bookingservice.config;

import com.aresky.bookingservice.dto.request.CreateBookingForm;
import com.aresky.bookingservice.exception.BookingException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

@Getter
@Component
@Scope("singleton")
public class BookingStorageConfig {

    public static final long expiration = 15 * 60 * 1000;

    private final Set<Element> elements = new HashSet<>();

    public Element checkElementAndCreateOrUpdate(Integer accountId, Integer subTourId){
        if(Boolean.TRUE.equals(isExistElement(accountId, subTourId))){
            Element element = getElement(accountId, subTourId);
            if(isExpiredElement(element)){
                element.expiration = Instant.now().plusMillis(BookingStorageConfig.expiration);
            } else {
                Duration duration = Duration.between(Instant.now(), element.expiration);
                long interval = duration.getSeconds();

                StringBuilder stringBuilder = new StringBuilder("Không thể tạo phiên thanh toán liên tục, hãy thử lại sau ");

                if(interval < 60){
                    stringBuilder.append(interval).append(" giây!");
                } else {
                    stringBuilder.append(interval / 60).append(" phút!");
                }

                throw new BookingException(stringBuilder.toString());
            }
        } else {
            addNewElement(accountId, subTourId);
        }

        return getElement(accountId, subTourId);
    }

    public Element getElement(Integer accountId, Integer subTourId){
        return elements.stream()
                .filter(e -> (Objects.equals(e.accountId, accountId) && Objects.equals(e.subTourId, subTourId)))
                .findFirst()
                .orElse(null);
    }

    public Element getElement(String title){
        return elements.stream()
                .filter(s -> s.title.equals(title))
                .findFirst().orElse(null);
    }

    public boolean isExistElement(Integer accountId, Integer subTourId){
        return elements.stream().anyMatch(session ->
                (Objects.equals(session.accountId, accountId) && Objects.equals(session.subTourId, subTourId))
        );
    }

    public boolean isExistElement(Element element){
        return elements.stream().anyMatch(element::equals);
    }

    public boolean isExpiredElement (Element element){
        return Instant.now().isAfter(element.expiration);
    }

    public void addNewElement (Integer accountId, Integer subTourId){
        if(Boolean.FALSE.equals(isExistElement(accountId, subTourId))) {
            elements.add(new Element(accountId, subTourId));
        }
    }

    public void removeElement (Element element){
        if(Boolean.TRUE.equals(isExistElement(element))){
            elements.removeIf(element::equals);
        }
    }

    @Getter
    @NoArgsConstructor
    public static class Element {
        @Setter
        private String title;

        private Integer accountId;
        private Integer subTourId;
        private Instant expiration;

        @Setter
        private CreateBookingForm bookingInfo;

        public Element(Integer accountId, Integer subTourId) {
            this.accountId = accountId;
            this.subTourId = subTourId;
            this.title = "SESSION_ACCOUNT_" + accountId + "_TOUR_" + subTourId;
            this.expiration = Instant.now().plusMillis(BookingStorageConfig.expiration);
        }

        @Override
        public boolean equals(Object obj) {
            if(this == obj){
                return true;
            }

            if(obj instanceof Element){
                boolean isSameAccount = this.accountId.equals(((Element) obj).accountId);
                boolean isSameTour = this.subTourId.equals(((Element) obj).subTourId);
                return isSameAccount && isSameTour;
            }

            return false;
        }

        @Override
        public String toString(){
            return "Session: [title = " + title + ", account = "  + accountId + ", subTour = " + subTourId + " ]";
        }
    }
}