import { Box, Button, Card, CardContent, IconButton, Stack, TextField, Typography } from "@mui/material";
import { useForm } from "react-hook-form";
import PersonOutlineIcon from '@mui/icons-material/PersonOutline';
import LockIcon from '@mui/icons-material/Lock';
import GoogleIcon from '@mui/icons-material/Google';
import FacebookIcon from '@mui/icons-material/Facebook';

function LoginPage() {

    const { register, handleSubmit } = useForm();

    const handleSubmitForm = (data) => {

        console.log(data);
    }

    return (
        <Box className="login-backGr" sx={{ pt: 5 }}>
            <form onSubmit={handleSubmit(handleSubmitForm)}>
                <center>
                    <Card sx={{ width: 400 }}>
                        <CardContent>
                            <Typography variant="h4" fontWeight="bold">
                                Login
                            </Typography>
                        </CardContent>
                        <CardContent>
                            <Box>
                                <Stack spacing={2} className="b-color-white">
                                    <TextField label="Username" variant="standard" InputProps={
                                        { startAdornment: <PersonOutlineIcon /> }
                                    } />
                                    <TextField label="Password" type="password" variant="standard" InputProps={
                                        { startAdornment: <LockIcon /> }
                                    } />
                                    <Typography sx={{ fontSize: "15px" }} align="right">Forgot password</Typography>
                                    <button type="submit" style={{ borderRadius: '200px', height: '40px' }} className="login-Btn">LOGIN</button>
                                    <Button variant="contained" type="button" color="error" endIcon={<GoogleIcon />}>Google</Button>
                                    <Button variant="contained" type="button" endIcon={<FacebookIcon />}>Facebook</Button>
                                </Stack>
                            </Box>
                        </CardContent>
                    </Card>
                </center>
            </form>
        </Box>
    );
}

export default LoginPage;