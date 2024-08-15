import { Button, Box, TextField, InputAdornment } from "@mui/material";
import CustomPaginate from "../../pagination/CustomPaginate";
import SearchIcon from "@mui/icons-material/Search";
import TouristAttractionCard from "./TouristAttractionCard";

const TouristAttractionManager = ({
  data,
  fields,
  handleChangeCurrentPage,
  handleShowCreate,
}) => {
  return (
    <Box className="administrator-manager">
      <section className="administrator-manager_header">
        <div className="administrator-manager__title">Danh sách điểm đến</div>
        <div className="administrator-manager__menu">
          <div className="administrator-manager__menu-item tours active">
            Điểm đến
          </div>
        </div>
      </section>
      <Box
        className="administrator-manager_main"
        sx={{
          display: "flex",
          flexDirection: "column",
          justifyContent: "center",
          gap: "20px",
        }}
      >
        <Box
          sx={{
            height: "80px",
            display: "flex",
            alignItems: "center",
            justifyContent: "space-between",
            marginTop: "15px",
          }}
        >
          <Box
            className="administrator-manager__actions--left"
            display={"flex"}
            gap={"15px"}
            width={"40%"}
          >
            <TextField
              type="text"
              name="name"
              label="Tìm kiếm"
              placeholder="nhập vào đây..."
              margin="normal"
              sx={{ minWidth: 100 }}
              variant="standard"
              InputProps={{
                startAdornment: (
                  <InputAdornment position="start">
                    <SearchIcon />
                  </InputAdornment>
                ),
              }}
            />
          </Box>
          <Box>
            <Button
              className="admin-btn admin-btn--create"
              onClick={handleShowCreate}
            >
              + Thêm mới địa điểm du lịch
            </Button>
          </Box>
        </Box>
        <Box
          sx={{
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
            justifyContent: "center",
            width: "100%",
            height: "100%",
          }}
        >
          {data ? (
            <Box
              sx={{
                width: "100%",
                // height: "100%",
              }}
            >
              <Box
                className="admin-tourist-attraction-list"
                sx={{
                  width: "100%",
                  display: "grid",
                  gridTemplateColumns: "repeat(auto-fit, minmax(300px, 1fr))",
                  gridAutoRows: "150px",
                  gap: "20px",
                }}
              >
                {/* <table className="admin-table">
                  <thead>
                    <tr>
                      <th className="admin-table-row_th __id">ID</th>
                      <th className="admin-table-row_th __name">Name</th>
                      <th className="admin-table-row_th __blogs">
                        Số bài viết
                      </th>
                      <th className="admin-table-row_th __action">Action</th>
                    </tr>
                  </thead>
                  <tbody>
                    {data?.content.map((item, index) => (
                      <tr key={index} className="admin-table-row">
                        <td>{item.id}</td>
                        <td>{item.name}</td>
                        <td>0</td>
                        <td className="admin-table-data__action">
                          <button
                            onClick={() => {
                              // navigate(
                              //   `/admin/tourist-attraction/details/${item.id}`
                              // );
                            }}
                          >
                            Xem
                          </button>
                        </td>
                      </tr>
                    ))}
                  </tbody>
                </table> */}
                {data.content?.map((item, index) => (
                  <TouristAttractionCard touristAttraction={item} key={index} />
                ))}
              </Box>
              <CustomPaginate
                currentPage={fields.page}
                totalPages={data?.totalPages}
                setCurrentPage={handleChangeCurrentPage}
              />
            </Box>
          ) : (
            <Box
              sx={{
                fontSize: "1.4rem",
                color: "var(--gray-color)",
              }}
            >
              Danh sách trống!
            </Box>
          )}
        </Box>
      </Box>
    </Box>
  );
};

export default TouristAttractionManager;
