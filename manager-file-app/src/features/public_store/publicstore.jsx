import { useForm } from "react-hook-form";
import publicStoreAPI from "../../api/publicstore";
import { Box, Button, Card, CardActions, CardContent, styled } from "@mui/material";
import uploadLogo from '../../assets/images/logos/upload.png';
import { Image } from "antd";
import { useState } from "react";
import CloudUploadIcon from '@mui/icons-material/CloudUpload';

function PublicStorePage() {

    const { handleSubmit, register } = useForm();
    const [fileName, setFileName] = useState('');

    const VisuallyHiddenInput = styled('input')({
        clip: 'rect(0 0 0 0)',
        clipPath: 'inset(50%)',
        height: 1,
        overflow: 'hidden',
        position: 'absolute',
        bottom: 0,
        left: 0,
        whiteSpace: 'nowrap',
        width: 1,
    });

    const getData = async (data) => {
        console.log(data.fileData[0]);
        const formData = new FormData();
        formData.append("fileData", data.fileData[0]);
        const response = await publicStoreAPI.uploadFile(formData);
        console.log(response);
    }
    return (
        <>
            <form id="upload" encType='multipart/form-data' onSubmit={handleSubmit(getData)}>
                <Card>
                    <CardContent>
                        URL
                    </CardContent>
                </Card>
                <Card sx={{ width: '700px', height: '300px' }}>
                    <CardContent>
                        <Box>
                            <Button
                                component="label"
                                role={undefined}
                                variant="text"
                                tabIndex={-1}
                            >
                                <Image width={150} height={150} preview={false} src={uploadLogo} />
                                <VisuallyHiddenInput type="file" {...register("fileData")} name="fileData" onChange={(e) => setFileName(e.target.files[0].name)} />
                            </Button>
                        </Box>
                        <Box>
                            {fileName}
                        </Box>
                    </CardContent>
                    <CardActions>
                        <Box flexGrow={1}>
                            <Button variant="contained" startIcon={<CloudUploadIcon />}>Upload</Button>
                        </Box>
                    </CardActions>
                </Card>
            </form>
        </>
    );
}

export default PublicStorePage;