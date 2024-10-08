import { useForm } from "react-hook-form";
import publicStoreAPI from "../../api/publicstore";
import { Box, Button, Card, CardActions, CardContent, Container, IconButton, styled, TextField } from "@mui/material";
import uploadLogo from '../../assets/images/logos/upload.png';
import { Image } from "antd";
import { useState } from "react";
import CloudUploadIcon from '@mui/icons-material/CloudUpload';
import DownloadIcon from '@mui/icons-material/Download';

function PublicStorePage() {

    const { handleSubmit, register } = useForm();
    const [fileName, setFileName] = useState('');
    const [defaultText, setDefaultText] = useState('');

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
        console.log(data);
        const formData = new FormData();
        formData.append("fileData", data.fileData[0]);
        await publicStoreAPI.uploadFile(formData).then((response) => {
            setDefaultText(response.data.url);
        });
    }

    const handleDowloadImage = () => {

        if (defaultText != null && defaultText != '') {
            window.open(defaultText, '_blank');
        }
    };

    return (
        <>
            <Container sx={{ pt: 3 }}>
                <Card>
                    <CardContent>
                        <TextField placeholder="Image URL" value={defaultText} disabled fullWidth size="small" InputProps={{
                            endAdornment: <IconButton onClick={handleDowloadImage}><DownloadIcon /></IconButton>
                        }} />
                    </CardContent>
                    <form id="publicStore" onSubmit={handleSubmit(getData)}>
                        <CardContent>
                            <Box>
                                <center>
                                    <Button
                                        component="label"
                                        role={undefined}
                                        variant="text"
                                        tabIndex={-1}
                                    >
                                        <Image width={150} height={150} preview={false} src={uploadLogo} />
                                        <VisuallyHiddenInput type="file" {...register("fileData")} name="fileData" onClick={() => { setDefaultText('') }} onChangeCapture={(e) => { setFileName(e.currentTarget.value) }} />
                                    </Button>
                                    <Box>
                                        {fileName}
                                    </Box>
                                    <Box sx={{ pt: 5 }}>
                                        <Button variant="contained" type="submit" startIcon={<CloudUploadIcon />}>Upload</Button>
                                    </Box>
                                </center>
                            </Box>
                        </CardContent>
                    </form>
                </Card>
            </Container>
        </>
    );
}

export default PublicStorePage;