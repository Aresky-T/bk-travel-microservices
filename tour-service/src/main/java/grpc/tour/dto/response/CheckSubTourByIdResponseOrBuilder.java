// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tour/dto/response/check-subtour-by-id-response.proto

// Protobuf Java Version: 3.25.1
package grpc.tour.dto.response;

public interface CheckSubTourByIdResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:response.CheckSubTourByIdResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>bool is_exists = 1;</code>
   * 
   * @return The isExists.
   */
  boolean getIsExists();

  /**
   * <code>.response.SubTourResponse sub_tour = 2;</code>
   * 
   * @return Whether the subTour field is set.
   */
  boolean hasSubTour();

  /**
   * <code>.response.SubTourResponse sub_tour = 2;</code>
   * 
   * @return The subTour.
   */
  SubTourResponse getSubTour();

  /**
   * <code>.response.SubTourResponse sub_tour = 2;</code>
   */
  SubTourResponseOrBuilder getSubTourOrBuilder();
}