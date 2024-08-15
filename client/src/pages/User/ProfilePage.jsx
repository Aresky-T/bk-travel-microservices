import React from "react";
import HelmetTitle from "../../components/helmet/HelmetTitle";
import ProfileContainer from "../../containers/user/profile";

const ProfilePage = () => {
  return (
    <>
      <HelmetTitle title={"BK Travel - Hồ Sơ"} metaName={"meta-profile"} />
      <ProfileContainer />
    </>
  );
};

export default ProfilePage;
