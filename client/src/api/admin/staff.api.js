import axios from "axios";
import { BASE_ENDPOINTS } from "..";

const staffUrl = BASE_ENDPOINTS.STAFF;
const departmentUrl = BASE_ENDPOINTS.STAFF_DEPARTMENT;
const positionUrl = BASE_ENDPOINTS.STAFF_POSITION;

export const getAllDepartmentsApi = () => {
    return axios.get(departmentUrl)
}

export const getAllPositionsByDepartmentIdApi = (departmentId) => {
    return axios.get(positionUrl);
}

export const getAllStaffsApi = (pageable) => {
    return axios.get(staffUrl, {
        params: { ...pageable }
    });
}

export const getAllStaffsByDepartmentId = (departmentId) => {
    return axios.get(staffUrl + "/department-id/" + departmentId)
}

export const getDetailsStaffApi = (staffId) => {
    return axios.get(`${staffUrl}/id/${staffId}`);
}

export const getDetailsDepartmentApi = (departmentId) => {
    return axios.get(departmentUrl + "/id/" + departmentId);
}

export const getDetailsPositionApi = (positionId) => {
    return axios.get(positionUrl + "/id/" + positionId);
}

export const createStaffApi = (formData) => {
    return axios.post(staffUrl, formData);
}

export const updateStaffApi = (staffId, formData) => {
    return axios.patch(`${staffUrl}/fields`, formData, {
        params: { id: staffId }
    })
}