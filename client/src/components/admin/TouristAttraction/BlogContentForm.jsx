import { Button, TextField } from '@mui/material'

const BlogContentForm = ({ formik, content, removeBlogContent }) => {
    const handleChange = (e) => {
        if (e.target) {
            const { name, value } = e.target;
            const { listContents } = formik.values;
            const index = listContents.findIndex(obj => obj === content);

            if (index !== -1) {
                const updateListContents = [...listContents];
                content[name] = value;
                updateListContents[index] = content;
                formik.setFieldValue('listContents', updateListContents)
            }
        }
    }

    return (
        <div className='blog-content-form'>
            <TextField
                label="Phụ đề"
                fullWidth
                required
                margin='normal'
                name='subTitle'
                value={content.subTitle}
                onChange={handleChange}
            />

            <TextField
                label="Link ảnh"
                fullWidth
                margin='normal'
                name='image'
                value={content.image}
                onChange={handleChange}
            />

            <TextField
                label="Nội dung"
                multiline
                minRows={3}
                fullWidth
                required
                margin='normal'
                name='content'
                value={content.content}
                onChange={handleChange}
            />
            <Button type="button"
                className='admin-form__btn'
                onClick={() => removeBlogContent(content)}
            >
                Xóa phần này
            </Button>
        </div>
    )
}

export default BlogContentForm