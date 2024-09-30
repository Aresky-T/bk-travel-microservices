import React, { useCallback, useEffect, useState } from "react";
import BlogForm from "../../../../components/admin/TouristAttraction/blog/BlogForm";
import { useAdmin } from "../../../../redux/selector";
import {
  createTouristAttractionBlogApi,
  getDetailsBlogApi,
  updateTouristAttractionBlogApi,
} from "../../../../api/admin/tourist_attraction.api";
import {
  errorAlert,
  successAlert,
  warningAlert,
} from "../../../../config/sweetAlertConfig";
import { randomString } from "../../../../utils/random";
import ValidateUtils from "../../../../utils/validate";
import { useDispatch } from "react-redux";
import { onUpdateAdminIsReloadTouristAttraction } from "../../../../redux/slices/admin.slice";

const initFormData = {
  id: null,
  touristAttractionId: null,
  title: {
    name: "Tiêu đề",
    value: "",
    isValid: false,
  },
  imageUrl: {
    name: "Link ảnh",
    value: "",
    isValid: false,
  },
  intro: {
    name: "Phần mở đầu",
    value: "",
    isValid: false,
  },
  author: "BK TRAVEL",
  items: {
    name: "Danh sách mục",
    value: [],
    isValid: false,
  },
};

const BlogFormContainer = ({ isShowForm, onHiddenForm }) => {
  const [blogForm, setBlogForm] = useState(initFormData);
  const [blogItems, setBlogItems] = useState([]);

  const { touristAttractionManager } = useAdmin();
  const { currentBlog, touristAttraction, isReloadTouristAttraction } =
    touristAttractionManager;

  const dispatch = useDispatch();

  const handleChangeBlogForm = ({ target: { name, value } }) => {
    setBlogForm((prev) => ({
      ...prev,
      [name]: {
        ...prev[name],
        value: value,
      },
    }));
  };

  const handleValidateBlog = () => {
    return new Promise((resolve) => {
      const formData = {
        title: blogForm.title.value,
        imageUrl: blogForm.imageUrl.value,
        intro: blogForm.intro.value,
      };
      const validateResult = ValidateUtils({
        formData: formData,
        rules: {
          title: {
            required: true,
          },
          imageUrl: {
            required: true,
          },
          intro: {
            required: true,
          },
        },
        messages: {
          title_required: "Tiêu đề không được để trống!",
          imageUrl_required: "Link ảnh không được để trống!",
          intro_required: "Phần mở đầu không được để trống",
        },
      });

      resolve(validateResult);
    });
  };

  const handleValidateBlogItems = () => {
    return new Promise((resolve) => {
      const errors = [];

      blogItems.forEach((item) => {
        if (!item.isValid) {
          errors.push(item.id);
        }
      });

      resolve(blogItems.length > 0 && errors.length === 0);
    });
  };

  // const onUpdateFormField = (name, value) => {
  //   setBlogForm((prev) => ({ ...prev, [name]: value }));
  // };

  // const onUpdateMultipleFormField = (fields) => {
  //   setBlogForm((prev) => ({ ...prev, ...fields }));
  // };

  const onResetForm = () => {
    const resetForm = { ...initFormData };
    resetForm.id = null;
    resetForm.title.isValid = false;
    resetForm.title.value = "";
    resetForm.imageUrl.isValid = false;
    resetForm.imageUrl.value = "";
    resetForm.intro.isValid = false;
    resetForm.intro.value = "";
    resetForm.items = [];

    setBlogForm(resetForm);
    setBlogItems([]);
  };

  const handleCloseForm = () => {
    onResetForm();
    onHiddenForm();
  };

  const handleAddNewBlogIem = () => {
    const newBlogItem = {
      id: randomString(10),
      subTitle: "",
      imageUrl: "",
      content: "",
      isValid: false,
      isExists: false,
    };

    setBlogItems([...blogItems, newBlogItem]);
  };

  const handleUpdateBlogItem = (data) => {
    const newItems = blogItems.map((item) => {
      if (item.id === data.id) {
        return data;
      }
      return item;
    });
    setBlogItems(newItems);
  };

  const handleRemoveBlogItem = (selectedItem) => {
    // const newItems = blogItems.filter((item) => item.id !== selectedItem.id);
    const newItems = blogItems.map((item) => {
      if (item.id === selectedItem.id) {
        return { ...item, isDelete: true };
      }

      return item;
    });

    setBlogItems(newItems);
  };

  const handleSubmitUpdate = async () => {
    try {
      const validateBlogResult = await handleValidateBlog();
      const isValidItems = await handleValidateBlogItems();

      const updatedBlogForm = { ...blogForm };
      Object.keys({ title: "", imageUrl: "", intro: "" }).forEach((key) => {
        updatedBlogForm[key] = {
          ...updatedBlogForm[key],
          isValid: !validateBlogResult.errors.has(key),
        };
      });
      setBlogForm(updatedBlogForm);

      if (!validateBlogResult.isValid || !isValidItems) {
        warningAlert(
          "Cảnh báo",
          "Tồn tại thông tin không hợp lệ, vui lòng kiểm tra lại!",
          {
            cancelButtonText: "Kiểm tra lại",
          }
        );
      } else {
        const items = blogItems.map((item) => {
          const { id, subTitle, imageUrl, content, isExists, isDelete } = item;

          if (!isExists) {
            return { subTitle, imageUrl, content };
          }

          return { id, subTitle, imageUrl, content, isDelete };
        });

        const formData = {
          title: blogForm.title.value,
          imageUrl: blogForm.imageUrl.value,
          intro: blogForm.intro.value,
          author: blogForm.author,
          items: items,
        };

        const response = await updateTouristAttractionBlogApi(
          currentBlog.id,
          formData
        );
        if (response) {
          dispatch(onUpdateAdminIsReloadTouristAttraction(true));
          successAlert(
            "Thành công",
            "Cập nhật bài viết thành công!",
            "Tiếp tục"
          );
        }
      }
    } catch (error) {
      errorAlert(
        "Thất bại",
        "Cập nhật bài viết thất bại, vui lòng thử lại!",
        "Thử lại"
      );
      console.log(error);
    }
  };

  const handleSubmitCreate = async () => {
    try {
      const validateBlogResult = await handleValidateBlog();
      const isValidItems = await handleValidateBlogItems();

      const updatedBlogForm = { ...blogForm };
      Object.keys({ title: "", imageUrl: "", intro: "" }).forEach((key) => {
        updatedBlogForm[key] = {
          ...updatedBlogForm[key],
          isValid: !validateBlogResult.errors.has(key),
        };
      });
      setBlogForm(updatedBlogForm);

      if (!validateBlogResult.isValid || !isValidItems) {
        warningAlert(
          "Cảnh báo",
          "Tồn tại thông tin không hợp lệ, vui lòng kiểm tra lại!",
          {
            cancelButtonText: "Kiểm tra lại",
          }
        );
      } else {
        const items = blogItems.map((item) => {
          const { subTitle, imageUrl, content } = item;
          return { subTitle, imageUrl, content };
        });

        const formData = {
          title: blogForm.title.value,
          imageUrl: blogForm.imageUrl.value,
          intro: blogForm.intro.value,
          author: blogForm.author,
          touristAttractionId: touristAttraction.id,
          items: items,
        };

        console.log(formData);

        // const loading = toast.loading("Đang xử lý...");
        const response = await createTouristAttractionBlogApi(formData);
        if (response) {
          dispatch(onUpdateAdminIsReloadTouristAttraction(true));
          successAlert(
            "Thành công",
            "Đã tạo bài viết thành công, kiểm tra kết quả ở trong danh sách bài viết!",
            "Tiếp tục"
          );
        }
      }
    } catch (error) {
      errorAlert(
        "Thất bại",
        "Tạo bài viết thất bại, vui lòng thử lại!",
        "Thử lại"
      );
      console.log(error);
    }
  };

  const fetchDetailsBlog = useCallback(() => {
    if (currentBlog) {
      getDetailsBlogApi(currentBlog.id)
        .then((res) => {
          const { id, imageUrl, intro, items, title, touristAttractionId } =
            res.data;

          const newBlogForm = { ...blogForm };
          // update blogForm id;
          newBlogForm.id = id;

          // update blogForm touristAttractionId
          newBlogForm.touristAttractionId = touristAttractionId;

          // update blogForm intro;
          newBlogForm.intro.isValid = true;
          newBlogForm.intro.value = intro;

          // update blogForm imageUrl;
          newBlogForm.imageUrl.isValid = true;
          newBlogForm.imageUrl.value = imageUrl;

          // update blogForm imageUrl title;
          newBlogForm.title.isValid = true;
          newBlogForm.title.value = title;

          // update blogForm items;
          const reduceItems = items.map((item) => ({
            ...item,
            isValid: true,
            isExists: true,
          }));

          // update blogForm state
          setBlogForm({ ...newBlogForm });
          setBlogItems(reduceItems);

          //
          if (isReloadTouristAttraction) {
            dispatch(onUpdateAdminIsReloadTouristAttraction(false));
          }
        })
        .catch((err) => {});
    }

    // eslint-disable-next-line
  }, [currentBlog]);

  useEffect(() => {
    fetchDetailsBlog();
  }, [fetchDetailsBlog]);

  useEffect(() => {
    if (touristAttraction) {
      setBlogForm((prev) => ({
        ...prev,
        touristAttractionId: touristAttraction.id,
      }));
    }
  }, [touristAttraction]);

  useEffect(() => {
    if (isReloadTouristAttraction) {
      console.log("reloading tourist attraction...");
      fetchDetailsBlog();
    }

    // eslint-disable-next-line
  }, [isReloadTouristAttraction]);

  return (
    <div className={`blog-form-container${isShowForm ? " active" : ""}`}>
      <BlogForm
        title={currentBlog ? "Chi tiết bài viết" : "Tạo mới bài viết"}
        formData={blogForm}
        blogItems={blogItems}
        onChange={handleChangeBlogForm}
        onClose={handleCloseForm}
        onChangeForm={handleChangeBlogForm}
        onAddNewItem={handleAddNewBlogIem}
        onRemoveItem={handleRemoveBlogItem}
        onUpdateItem={handleUpdateBlogItem}
        onSubmitCreate={handleSubmitCreate}
        onSubmitUpdate={handleSubmitUpdate}
      />
    </div>
  );
};

export default BlogFormContainer;
