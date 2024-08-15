import React from 'react'
import { useNavigate } from 'react-router-dom'
import { ROUTE } from '../../../constant/route';
import { Box, Button, FormControl, InputLabel, MenuItem, Select, TextField } from '@mui/material';
import CustomDatePicker from '../../mui_component/datepicker';

const TourGuideDetails = ({ formik, handleDeleteTourGuide }) => {
    const navigate = useNavigate();

    return (
        <div className='admin-main tour-guide-details-container'>
            <h1>Chi tiết nhân viên</h1>
            <div className='back-to-prev-page_btn'>
                <span
                    onClick={() => {
                        navigate(ROUTE.STAFF_MANAGER)
                    }}
                >Quay lại</span>
            </div>
            <form className="admin-main-form t_guide-admin-form"
                onSubmit={formik.handleSubmit}
            >
                <TextField
                    name='fullName'
                    type='text'
                    fullWidth
                    required
                    margin='normal'
                    label='Họ tên đầy đủ'
                    value={formik.values.fullName}
                    onChange={formik.handleChange}
                />
                <Box sx={{
                    display: 'grid',
                    gridTemplateColumns: 'repeat(4, 1fr)',
                    columnGap: '20px'
                }}>
                    <FormControl required margin='normal' fullWidth>
                        <CustomDatePicker
                            label="Ngày sinh"
                            name='birthDate'
                            value={formik.values.birthDate}
                            setValue={(value) => {
                                formik.setFieldValue('birthDate', value['$d'])
                            }}
                        />
                    </FormControl>
                    <FormControl
                        fullWidth
                        margin='normal'
                    >
                        <InputLabel id='tour-guide-gender_label-id'>Giới tính</InputLabel>
                        <Select
                            name='gender'
                            labelId='tour-guide-gender_label-id'
                            label="Giới tính"
                            value={formik.values.gender}
                            onChange={formik.handleChange}
                        >
                            <MenuItem value=''><em>None</em></MenuItem>
                            <MenuItem value='MALE'
                                defaultChecked={formik.values.gender === 'MALE'}
                            >
                                Nam</MenuItem>
                            <MenuItem value='FEMALE'
                                defaultChecked={formik.values.gender === 'FEMALE'}
                            >
                                Nữ</MenuItem>
                            <MenuItem value='OTHER'
                                defaultChecked={formik.values.gender === 'OTHER'}
                            >
                                Khác</MenuItem>
                        </Select>
                    </FormControl>
                    <TextField
                        name='phone'
                        type='text'
                        fullWidth
                        required
                        margin='normal'
                        label='Số điện thoại'
                        value={formik.values.phone}
                        onChange={formik.handleChange}
                    />
                    <TextField
                        name='status'
                        type='text'
                        fullWidth
                        required
                        margin='normal'
                        label='Trạng thái hoạt động'
                        value={
                            formik.values.status === "BUSY" ?
                                `${formik.values.status} - (TourID: ${formik.values.tourId})`
                                :
                                `${formik.values.status}`
                        }
                        onChange={formik.handleChange}
                        InputProps={{
                            readOnly: true
                        }}
                    />
                    {/* <FormControl
            fullWidth
            margin='normal'
          >
            <InputLabel id='tour-guide-status_label-id'>Trạng thái hoạt động</InputLabel>
            <Select
              name='status'
              labelId='tour-guide-status_label-id'
              label="Trạng thái hoạt động"
              value={formik.values.status}
              onChange={formik.handleChange}
            >
              <MenuItem
                value='BUSY'
                defaultChecked={formik.values.status === 'BUSY'}
              >
                Bận (TourID: {formik.values.tourId})
              </MenuItem>
              <MenuItem
                value='AVAILABLE'
                defaultChecked={formik.values.status === 'AVAILABLE'}
              >
                Rảnh
              </MenuItem>
            </Select>
          </FormControl> */}
                </Box>
                <TextField
                    name='address'
                    type='text'
                    fullWidth
                    required
                    margin='normal'
                    label='Địa chỉ'
                    value={formik.values.address}
                    onChange={formik.handleChange}
                />
                <TextField
                    name='description'
                    type='text'
                    fullWidth
                    required
                    margin='normal'
                    label='Mô tả'
                    multiline
                    maxRows={10}
                    value={formik.values.description}
                    onChange={formik.handleChange}
                />
                <TextField
                    name='avatarUrl'
                    type='text'
                    fullWidth
                    required
                    margin='normal'
                    label='Link ảnh'
                    multiline
                    maxRows={10}
                    value={formik.values.avatarUrl}
                    onChange={formik.handleChange}
                />
                <Button className="admin-form__btn admin-form__btn--submit"
                    type='submit'
                >Cập nhật</Button>
                <Button className='admin-form__btn admin-form__btn--delete'
                    type='button'
                    onClick={handleDeleteTourGuide}
                >Xóa nhân viên</Button>
            </form>
        </div>
    )
}

export default TourGuideDetails