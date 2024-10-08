import { Box, Button, Container, Grid, IconButton, TextField } from "@mui/material";
import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { useLocation, useNavigate } from "react-router-dom";
import fileAPI from "../../api/file-api";
import { Image } from "antd";
import DownloadIcon from '@mui/icons-material/Download';
import fileImage from '../../assets/images/logos/file.png';
import UploadIcon from '@mui/icons-material/Upload';
import DeleteIcon from '@mui/icons-material/Delete';
import queryString from 'querystring';
import Swal from "sweetalert2";

function OpenFolderPage() {

    const location = useLocation();
    const jwt = useSelector(state => state.authUserReducer.authUser.jwt);
    const [fileData, setFileData] = useState([]);
    const navigate = useNavigate();
    const folderId = queryString.parse(location.search).folderId || location.state.folderData.folderId;

    const handleDowloadFileData = (url) => {
        window.open(url, '_blank');
    };

    const handleDeleteFile = (data) => {

        const newData = {
            ...data,
            status: 0,
        };
        Swal.fire({
            title: "Are you sure?",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "Yes, delete it!"
        }).then((result) => {
            if (result.isConfirmed) {
                fileAPI.uploadStatusDelete(newData, jwt).then(response => {
                    Swal.fire({
                        title: "Deleted!",
                        text: "Your file has been deleted.",
                        icon: "success"
                    });
                    const cloneFileData = fileData.filter(item => item.fileId != data.fileId);
                    setFileData(cloneFileData);
                }).catch(err => {
                    console.log(err);
                });
            }
        });
    }

    const handleOpenUploadPage = () => {

        navigate("/upload-file", {
            state: {
                folderId: folderId
            }
        });
    };

    useEffect(() => {

        const fetchData = async () => {

            await fileAPI.getAllFileByFolderAndUser(folderId, jwt).then(response => {

                setFileData(response.data);
            }).catch(err => {

                console.log(err);
            });
        };
        fetchData();
    }, [])

    return (
        <Container sx={{ mt: 2 }} maxWidth="xl">

            <Box>
                <Button variant="contained" onClick={handleOpenUploadPage} endIcon={<UploadIcon />}>Upload</Button>
            </Box>

            <Grid container sx={{ mt: 2 }}>
                {fileData?.map(item => (
                    <Grid key={item.fileId} lg="2" sx={{ p: 1 }}>
                        <Box className="img-frame">
                            <Box>
                                <IconButton sx={{ position: "absolute" }} onClick={() => handleDeleteFile(item)} size="small"><DeleteIcon /></IconButton>
                                <center>
                                    {
                                        <Image className="img-autofill" height='180px' width='150px' src={item.fileType == 1 ? item.fileUrl : fileImage} alt={item.fileName} />
                                    }
                                </center>
                            </Box>
                            <Box>
                                <center>
                                    <TextField value={item.fileName} disabled InputProps={{
                                        endAdornment: <IconButton onClick={() => { handleDowloadFileData(item.fileUrl) }} color="primary"><DownloadIcon /></IconButton>
                                    }} />
                                </center>
                            </Box>
                        </Box>
                    </Grid>
                ))}
            </Grid>
        </Container>
    );
}

export default OpenFolderPage;