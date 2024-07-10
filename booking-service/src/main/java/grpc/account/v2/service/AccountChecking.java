// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: account/v2/services/account-checking.proto

// Protobuf Java Version: 3.25.1
package grpc.account.v2.service;

public final class AccountChecking {
  private AccountChecking() {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }

  public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }

  private static com.google.protobuf.Descriptors.FileDescriptor descriptor;
  static {
    String[] descriptorData = {
        "\n*account/v2/services/account-checking.p" +
            "roto\022\007service\0328account/v2/dto/request/ch" +
            "eck-account-by-id-request.proto\032;account" +
            "/v2/dto/request/check-account-by-email-r" +
            "equest.proto\032>account/v2/dto/request/che" +
            "ck-account-by-username-request.proto\032@ac" +
            "count/v2/dto/request/check-profile-by-ac" +
            "count-id-request.proto\032;account/v2/dto/r" +
            "equest/check-profile-by-email-request.pr" +
            "oto\032:account/v2/dto/response/check-accou" +
            "nt-by-id-response.proto\032=account/v2/dto/" +
            "response/check-account-by-email-response" +
            ".proto\032@account/v2/dto/response/check-ac" +
            "count-by-username-response.proto\032Baccoun" +
            "t/v2/dto/response/check-profile-by-accou" +
            "nt-id-response.proto\032=account/v2/dto/res" +
            "ponse/check-profile-by-email-response.pr" +
            "oto2\235\004\n\026AccountCheckingService\022Z\n\020CheckA" +
            "ccountById\022 .request.CheckAccountByIdReq" +
            "uest\032\".response.CheckAccountByIdResponse" +
            "\"\000\022c\n\023CheckAccountByEmail\022#.request.Chec" +
            "kAccountByEmailRequest\032%.response.CheckA" +
            "ccountByEmailResponse\"\000\022l\n\026CheckAccountB" +
            "yUsername\022&.request.CheckAccountByUserna" +
            "meRequest\032(.response.CheckAccountByUsern" +
            "ameResponse\"\000\022o\n\027CheckProfileByAccountId" +
            "\022\'.request.CheckProfileByAccountIdReques" +
            "t\032).response.CheckProfileByAccountIdResp" +
            "onse\"\000\022c\n\023CheckProfileByEmail\022#.request." +
            "CheckProfileByEmailRequest\032%.response.Ch" +
            "eckProfileByEmailResponse\"\000B\033\n\027grpc.acco" +
            "unt.v2.serviceP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
        .internalBuildGeneratedFileFrom(descriptorData,
            new com.google.protobuf.Descriptors.FileDescriptor[] {
                grpc.account.v2.dto.request.CheckAccountByIdRequestOuterClass.getDescriptor(),
                grpc.account.v2.dto.request.CheckAccountByEmailRequestOuterClass.getDescriptor(),
                grpc.account.v2.dto.request.CheckAccountByUsernameRequestOuterClass.getDescriptor(),
                grpc.account.v2.dto.request.CheckProfileByAccountIdRequestOuterClass.getDescriptor(),
                grpc.account.v2.dto.request.CheckProfileByEmailRequestOuterClass.getDescriptor(),
                grpc.account.v2.dto.response.CheckAccountByIdResponseOuterClass.getDescriptor(),
                grpc.account.v2.dto.response.CheckAccountByEmailResponseOuterClass.getDescriptor(),
                grpc.account.v2.dto.response.CheckAccountByUsernameResponseOuterClass.getDescriptor(),
                grpc.account.v2.dto.response.CheckProfileByAccountIdResponseOuterClass.getDescriptor(),
                grpc.account.v2.dto.response.CheckProfileByEmailResponseOuterClass.getDescriptor(),
            });
    grpc.account.v2.dto.request.CheckAccountByIdRequestOuterClass.getDescriptor();
    grpc.account.v2.dto.request.CheckAccountByEmailRequestOuterClass.getDescriptor();
    grpc.account.v2.dto.request.CheckAccountByUsernameRequestOuterClass.getDescriptor();
    grpc.account.v2.dto.request.CheckProfileByAccountIdRequestOuterClass.getDescriptor();
    grpc.account.v2.dto.request.CheckProfileByEmailRequestOuterClass.getDescriptor();
    grpc.account.v2.dto.response.CheckAccountByIdResponseOuterClass.getDescriptor();
    grpc.account.v2.dto.response.CheckAccountByEmailResponseOuterClass.getDescriptor();
    grpc.account.v2.dto.response.CheckAccountByUsernameResponseOuterClass.getDescriptor();
    grpc.account.v2.dto.response.CheckProfileByAccountIdResponseOuterClass.getDescriptor();
    grpc.account.v2.dto.response.CheckProfileByEmailResponseOuterClass.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
