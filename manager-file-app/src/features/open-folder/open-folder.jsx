import { Box, Button, Container, Grid, IconButton, TextField } from "@mui/material";
import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { useLocation } from "react-router-dom";
import fileAPI from "../../api/file-api";
import { Image } from "antd";
import DownloadIcon from '@mui/icons-material/Download';
import fileImage from '../../assets/images/logos/file.png';
import UploadIcon from '@mui/icons-material/Upload';

function OpenFolderPage() {

    const location = useLocation();
    const jwt = useSelector(state => state.authUserReducer.authUser.jwt);
    const [fileData, setFileData] = useState([]);
    const folderId = location.state.folderData.folderId;

    const handleDowloadFileData = (url) => {
        window.open(url, '_blank');
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
                <Button variant="contained" endIcon={<UploadIcon />}>Upload</Button>
            </Box>

            <Grid container sx={{ mt: 2 }}>
                {fileData?.map(item => (
                    <Grid key={item.fileId} xl={2} sx={{ p: 1 }}>
                        <Box className="img-frame">
                            <Box>
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