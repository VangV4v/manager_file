import { Box, Button, Container, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle, TextField, Typography } from "@mui/material";
import AddIcon from '@mui/icons-material/Add';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import { useEffect, useState } from "react";
import folderAPI from "../../api/folder-api";
import { useSelector } from "react-redux";
import { useForm } from "react-hook-form";

function HomePage() {

    const [folderData, setFolderData] = useState([]);
    const jwt = useSelector(state => state.authUserReducer.authUser.jwt);

    const handleDoubleClicRow = () => {

        console.log("Double click");
    };
    //dialog
    const [open, setOpen] = useState(false);
    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    const { register, handleSubmit } = useForm();

    const handleCreateFolder = (data) => {

        console.log(data);
        folderAPI.createFolderByUser(jwt, data).then(response => {

            console.log(response.data);
        }).catch(err => {

            console.log(err);
        });
        handleClose();
    };
    //end dialog

    useEffect(() => {

        const loadFolderData = async () => {

            await folderAPI.getFoldersByUser(jwt).then(response => {
                setFolderData(response.data);
            }).catch(err => {
                console.log(err);
            });

        };
        loadFolderData();
    }, []);

    return (
        <Container maxWidth="xl" sx={{ mt: 1 }}>
            <Box>
                <Button variant="outlined" onClick={handleClickOpen} endIcon={<AddIcon />}>CREATE FOLDER</Button>
            </Box>
            <Box sx={{ pt: 5 }}>
                <TableContainer component={Paper}>
                    <Table sx={{ minWidth: 650 }} aria-label="simple table">
                        <TableHead>
                            <TableRow>
                                <TableCell><Typography fontWeight="bold">Folder Name</Typography></TableCell>
                                <TableCell align="right"><Typography fontWeight="bold">Count Of File</Typography></TableCell>
                                <TableCell align="right"><Typography fontWeight="bold">Created Date</Typography></TableCell>
                                <TableCell align="right">Setting</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {folderData?.map((row) => (
                                <TableRow
                                    hover
                                    key={row.folderId}
                                    sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                                    onDoubleClick={handleDoubleClicRow}
                                >
                                    <TableCell component="th" scope="row">
                                        {row.folderName}
                                    </TableCell>
                                    <TableCell align="right">{row.fileInFolder}</TableCell>
                                    <TableCell align="right">{row.createdDate}</TableCell>
                                    <TableCell align="right">{row.folderName}</TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            </Box>
            <Box>
                <form onSubmit={handleSubmit(handleCreateFolder)}>
                    <Dialog
                        open={open}
                        onClose={handleClose}
                        PaperProps={{
                            component: 'form',

                        }}
                    >
                        <DialogTitle>Create Folder</DialogTitle>
                        <DialogContent sx={{ width: '500px' }}>
                            <TextField
                                autoFocus
                                required
                                margin="dense"
                                label="Folder Name"
                                type="text"
                                {...register("folderName")}
                                fullWidth
                                variant="standard"
                            />
                        </DialogContent>
                        <DialogActions>
                            <Button variant="contained" onClick={handleClose}>CLOSE</Button>
                            <Button variant="contained" type="submit">CREATE</Button>
                        </DialogActions>
                    </Dialog>
                </form>
            </Box>
        </Container>
    );
}

export default HomePage;