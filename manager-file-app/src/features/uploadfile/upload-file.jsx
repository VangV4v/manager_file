import { Button } from "@mui/material";
import { useForm } from "react-hook-form";
import { useLocation } from "react-router-dom";
import fileAPI from "../../api/file-api";
import { useSelector } from "react-redux";

function UploadFilePage(props) {

    const location = useLocation();
    const folderId = location.state.folderId;
    const jwt = useSelector(state => state.authUserReducer.authUser.jwt);
    const { register, handleSubmit } = useForm();
    const handleSubmitForm = (data) => {

        const formData = new FormData();
        formData.append("fileData", data.fileData[0]);
        formData.append("fileName", data.fileName);
        formData.append("folderId", folderId);
        fileAPI.uploadFileByUser(formData, jwt).then(response => {
            console.log(response);
        }).catch(err => {
            console.log(err);
        });
    };

    return (
        <>
            <form onSubmit={handleSubmit(handleSubmitForm)}>

                <input type="file" {...register("fileData")} />
                <input {...register("fileName")} />
                <Button type="submit">Upload</Button>
            </form>
        </>
    );
}

export default UploadFilePage;