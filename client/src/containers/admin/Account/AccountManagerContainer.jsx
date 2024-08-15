import { useEffect, useState } from "react";
import AccountManager from "../../../components/admin/Account/AccountManager";
import { useAdmin } from "../../../redux/selector";
import AccountUpdateContainer from "./update";
import { useDispatch } from "react-redux";
import {
  fetchAllAccountsThunk,
  onRefetchAdminAccountList,
} from "../../../redux/slices/admin.slice";
import { deleteAccountApi } from "../../../api/admin/account.api";
import { toast } from "react-toastify";
import { questionAlert } from "../../../config/sweetAlertConfig";

const AccountManagerContainer = () => {
  const [paginate, setPaginate] = useState({
    size: 5,
    pageNumber: 0,
    sort: "username,asc",
  });

  const dispatch = useDispatch();
  const { accountManager } = useAdmin();
  const { isRefetch, accountList, totalPages, pageNumber, totalElements } =
    accountManager;

  const handleChangeCurrentPage = (page) => {
    setPaginate({
      ...paginate,
      pageNumber: page,
    });
  };

  const fetchAllAccounts = () => {
    dispatch(
      fetchAllAccountsThunk({
        page: paginate.pageNumber,
        size: paginate.size,
        sort: paginate.sort,
      })
    );
  };

  const handleDeleteAccount = (account) => {
    if (!account) return;

    questionAlert(
      "Cảnh báo",
      "Bạn có chắc chắn muốn xoá tài khoản này?",
      "Xoá",
      "Huỷ bỏ"
    )
      .then((btn) => {
        if (btn.isConfirmed) {
          deleteAccountApi(account.id)
            .then((res) => {
              dispatch(onRefetchAdminAccountList());
              toast.success("Xoá tài khoản thành công", {
                position: "top-right",
              });
            })
            .catch((err) => {
              toast.error("Xoá tài khoản thất bại", { position: "top-right" });
            });
        }
      })
      .catch((err) => {});
  };

  useEffect(() => {
    fetchAllAccounts();

    //eslint-disable-next-line
  }, [paginate]);

  useEffect(() => {
    isRefetch && fetchAllAccounts();

    //eslint-disable-next-line
  }, [isRefetch]);

  useEffect(() => {
    if (pageNumber >= totalPages) {
      handleChangeCurrentPage(totalPages - 1);
    }

    //eslint-disable-next-line
  }, [pageNumber, totalPages]);

  return (
    <div className="administrator-manager-container">
      <AccountManager
        accounts={accountList}
        pageNumber={paginate.pageNumber}
        totalPages={totalPages}
        totalElements={totalElements}
        handleChangeCurrentPage={handleChangeCurrentPage}
        handleDeleteAccount={handleDeleteAccount}
      />
      <AccountUpdateContainer />
    </div>
  );
};

export default AccountManagerContainer;
