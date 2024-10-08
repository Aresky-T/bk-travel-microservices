export const StaffData = [
  {
    id: 1,
    firstName: "Alice",
    lastName: "Smith",
    avatarUrl: "https://example.com/avatar2.jpg",
    gender: "FEMALE",
    dateOfBirth: "1985-09-10",
    email: "alice.smith@example.com",
    phone: "9876543210",
    address: "456 Elm St, Somewhereville, USA",
    description: "Description...",
    position: "Software Engineer",
    basicSalary: 60000,
    startedDate: "2012-08-15",
    contractUrl: "https://example.com/contract2.pdf",
    status: "ACTIVE",
  },
  {
    id: 2,
    firstName: "Bob",
    lastName: "Johnson",
    avatarUrl: "https://example.com/avatar3.jpg",
    gender: "",
    dateOfBirth: "1988-03-25",
    email: "bob.johnson@example.com",
    phone: "5551234567",
    address: "789 Oak St, Everywhereville, USA",
    description: "Description...",
    position: "Product Manager",
    basicSalary: 75000,
    startedDate: "2014-05-01",
    contractUrl: "https://example.com/contract3.pdf",
    status: "ACTIVE",
  },
  {
    id: 3,
    firstName: "John",
    lastName: "Doe",
    avatarUrl: "https://example.com/avatar1.jpg",
    gender: "MALE",
    dateOfBirth: "1990-05-15",
    email: "john.doe@example.com",
    phone: "1234567890",
    address: "123 Main St, Anytown, USA",
    description: "Description...",
    position: "Senior Developer",
    basicSalary: 50000,
    startedDate: "2010-03-20",
    contractUrl: "https://example.com/contract1.pdf",
    status: "ACTIVE",
  },
  {
    id: 4,
    firstName: "Takeshi",
    lastName: "Tanaka",
    avatarUrl: "https://example.com/avatar3.jpg",
    gender: "MALE",
    dateOfBirth: "1988-10-25",
    email: "takeshi.tanaka@example.com",
    phone: "0901234567",
    address: "789 Sakura St, Tokyotown, Japan",
    description: "Description...",
    position: "Software Engineer",
    basicSalary: 60000,
    startedDate: "2012-04-15",
    contractUrl: "https://example.com/contract3.pdf",
    status: "ACTIVE",
  },
  {
    id: 5,
    firstName: "Hoa",
    lastName: "Nguyen",
    avatarUrl: "https://example.com/avatar4.jpg",
    gender: "FEMALE",
    dateOfBirth: "1993-09-12",
    email: "hoa.nguyen@example.com",
    phone: "0987654321",
    address: "456 Hoa Mai St, Saigon, Vietnam",
    description: "Description...",
    position: "Graphic Designer",
    basicSalary: 45000,
    startedDate: "2014-08-20",
    contractUrl: "https://example.com/contract4.pdf",
    status: "ACTIVE",
  },
  {
    id: 6,
    firstName: "Nguyễn",
    lastName: "Anh Tuấn",
    avatarUrl: "https://example.com/avatar5.jpg",
    gender: "MALE",
    dateOfBirth: "1990-01-01",
    email: "tuan.nguyen@example.com",
    phone: "0987654321",
    address: "789 Lê Lợi, Hà Nội, Vietnam",
    description: "Description...",
    position: "Senior Developer",
    basicSalary: 100000,
    startedDate: "2010-01-01",
    contractUrl: "https://example.com/contract5.pdf",
    status: "ACTIVE",
  },
  {
    id: 7,
    firstName: "Ngọc",
    lastName: "Trần",
    avatarUrl: "https://example.com/avatar6.jpg",
    gender: "FEMALE",
    dateOfBirth: "1985-07-15",
    email: "ngoc.tran@example.com",
    phone: "0912345678",
    address: "123 Đinh Tiên Hoàng, Đà Nẵng, Vietnam",
    description: "Description...",
    position: "Project Manager",
    basicSalary: 80000,
    startedDate: "2008-06-10",
    contractUrl: "https://example.com/contract6.pdf",
    status: "ACTIVE",
  },
];

export const DepartmentsData = [
  {
    id: 1,
    name: "Phòng Kinh Doanh",
    description:
      "Phòng chịu trách nhiệm về việc tìm kiếm và phát triển các dịch vụ du lịch mới",
    manager: { id: 101, name: "Nguyễn Văn A", nationality: "Vietnamese" },
    totalMembers: 15,
  },
  {
    id: 2,
    name: "Phòng Marketing",
    description:
      "Phòng chịu trách nhiệm về việc quảng bá và tiếp thị các tour du lịch",
    manager: { id: 102, name: "Trần Thị B", nationality: "Vietnamese" },
    totalMembers: 12,
  },
  {
    id: 3,
    name: "Phòng Kế Toán",
    description:
      "Phòng chịu trách nhiệm về việc quản lý tài chính và kế toán của công ty",
    manager: { id: 103, name: "Lê Văn C", nationality: "Vietnamese" },
    totalMembers: 8,
  },
  {
    id: 4,
    name: "Phòng Sales",
    description: "Phòng chịu trách nhiệm về việc tăng doanh số bán hàng",
    manager: { id: 104, name: "John Smith", nationality: "American" },
    totalMembers: 10,
  },
  {
    id: 5,
    name: "Phòng Phát Triển Sản Phẩm",
    description:
      "Phòng chịu trách nhiệm phát triển và cải thiện sản phẩm du lịch",
    manager: { id: 105, name: "Yamada Satoshi", nationality: "Japanese" },
    totalMembers: 7,
  },
  {
    id: 6,
    name: "Phòng Hỗ Trợ Khách Hàng",
    description:
      "Phòng chịu trách nhiệm hỗ trợ và giải đáp thắc mắc của khách hàng",
    manager: { id: 106, name: "Kim Ji Hye", nationality: "Korean" },
    totalMembers: 9,
  },
  {
    id: 7,
    name: "Phòng Giám Đốc",
    description:
      "Phòng chịu trách nhiệm quản lý và điều hành toàn bộ hoạt động của công ty",
    manager: { id: 107, name: "Aresky Tuan", nationality: "Your Nationality" },
    totalMembers: 1,
  },
];
