import { AppBar, Box, Button, Toolbar } from "@mui/material";
import BackupTwoToneIcon from '@mui/icons-material/BackupTwoTone';
import UploadIcon from '@mui/icons-material/Upload';
import LoginIcon from '@mui/icons-material/Login';

function HeaderPage() {
    return (
        <Box>
            <AppBar>
                <Toolbar disableGutters>
                    <Box>
                        <Button color='inherit' startIcon={<BackupTwoToneIcon />}>VFILE</Button>
                    </Box>
                    <Box flexGrow={1}>
                        <Button color='inherit' href='/public-store/' endIcon={<UploadIcon />}>Upload</Button>
                        <Button color='inherit' endIcon={<LoginIcon />}>Login</Button>
                    </Box>
                </Toolbar>
            </AppBar>
        </Box>
    );
}

export default HeaderPage;