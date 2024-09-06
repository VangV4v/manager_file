import { Box, Button, Card, CardContent, IconButton, Stack, TextField, Typography } from "@mui/material";
import { useForm } from "react-hook-form";
import PersonOutlineIcon from '@mui/icons-material/PersonOutline';
import LockIcon from '@mui/icons-material/Lock';
import GoogleIcon from '@mui/icons-material/Google';
import FacebookIcon from '@mui/icons-material/Facebook';
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from "yup";
import { useDispatch } from "react-redux";
import { authenticateUser } from "../../slice/auth-user-slice";

const schema = yup.object({
    username: yup.string().required("Username is not empty"),
    password: yup.string().required("Password is not empty"),
}).required();

function LoginPage() {

    const dispatch = useDispatch();
    const { register, handleSubmit, formState: { errors } } = useForm({
        resolver: yupResolver(schema)
    });

    const handleSubmitForm = (data) => {

        const action = authenticateUser(data);
        dispatch(action)
            .unwrap()
            .then((response) => {
                localStorage.setItem('authUserInformation', JSON.stringify(response));
            })
            .catch((error) => {
                console.log(error);
            })
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
                                    <Box sx={{ display: 'flex', alignItems: 'flex-end' }}>
                                        <PersonOutlineIcon sx={{ color: 'action.active', mr: 1, my: 0.5 }} />
                                        <TextField id="input-with-sx" error={!!errors.username} helperText={errors.username?.message} {...register("username")} fullWidth label="Username" variant="standard" />
                                    </Box>
                                    <Box sx={{ display: 'flex', alignItems: 'flex-end' }}>
                                        <LockIcon sx={{ color: 'action.active', mr: 1, my: 0.5 }} />
                                        <TextField id="input-with-sx" type="password" error={!!errors.password} helperText={errors.password?.message} {...register("password")} fullWidth label="Password" variant="standard" />
                                    </Box>
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