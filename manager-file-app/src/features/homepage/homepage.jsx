import { Alert, Box, Button, Container, Dialog, DialogActions, DialogContent, DialogTitle, IconButton, Menu, MenuItem, TextField, Typography } from "@mui/material";
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
import CheckIcon from '@mui/icons-material/Check';
import DensityMediumIcon from '@mui/icons-material/DensityMedium';
import DeleteIcon from '@mui/icons-material/Delete';
import Swal from "sweetalert2";

function HomePage() {

    const [folderData, setFolderData] = useState([]);
    const jwt = useSelector(state => state.authUserReducer.authUser.jwt);
    const [isShowNotify, setShowNotify] = useState(false);
    const [anchorElUser, setAnchorElUser] = useState(null);
    const [folderId, setFolderId] = useState();

    const handleDoubleClicRow = () => {

        console.log("Double click");
    };
    //menu item
    const handleOpenUserMenu = (event) => {
        setAnchorElUser(event.currentTarget);
    };
    const handleCloseUserMenu = () => {
        setAnchorElUser(null);
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

            const cloneFolderData = [...folderData];
            cloneFolderData.push(response.data.folderData);
            setFolderData(cloneFolderData);
            displayNotification();
        }).catch(err => {

            console.log(err);
        });
        handleClose();
    };
    //end dialog
    //delete
    const handleDeleteFolder = (event, data) => {

        console.log(folderId);
        console.log(data);
        console.log(event);
        const dataAfterUpdate = {
            ...data,
            status: 0
        };
        setAnchorElUser(null);
        // Swal.fire({
        //     title: "Are you sure?",
        //     text: "We switch your folder to trash!",
        //     icon: "warning",
        //     showCancelButton: true,
        //     confirmButtonColor: "#3085d6",
        //     cancelButtonColor: "#d33",
        //     confirmButtonText: "Yes, delete it!"
        // }).then((result) => {
        //     if (result.isConfirmed) {

        //         folderAPI.updateToTrash(jwt, dataAfterUpdate).then(response => {
        //             Swal.fire({
        //                 title: "Deleted!",
        //                 text: "Your folder has been deleted.",
        //                 icon: "success"
        //             });
        //         }).catch(err => {
        //             console.log(err);
        //         });
        //     }
        // });
    };

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

    function displayNotification() {

        setShowNotify(true);
        setTimeout(() => {
            setShowNotify(false);
        }, 3000);
    }

    return (
        <Container maxWidth="xl" sx={{ mt: 1 }}>
            <Box>
                <Button variant="outlined" onClick={handleClickOpen} endIcon={<AddIcon />}>CREATE FOLDER</Button>
                {
                    isShowNotify &&
                    <Alert sx={{ mt: 2 }} icon={<CheckIcon fontSize="inherit" />} severity="success">
                        Create folder successfully
                    </Alert>
                }
            </Box>
            <Box sx={{ pt: 5 }}>
                <TableContainer component={Paper}>
                    <Table sx={{ minWidth: 650 }} aria-label="simple table">
                        <TableHead>
                            <TableRow>
                                <TableCell><Typography fontWeight="bold">Folder Name</Typography></TableCell>
                                <TableCell align="right"><Typography fontWeight="bold">Count Of File</Typography></TableCell>
                                <TableCell align="right"><Typography fontWeight="bold">Created Date</Typography></TableCell>
                                <TableCell align="right"></TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {folderData?.map((row, idx) => (
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
                                    <TableCell align="right">
                                        <IconButton onClick={handleOpenUserMenu}><DensityMediumIcon /></IconButton>
                                        <Menu
                                            sx={{ width: '300px' }}
                                            anchorEl={anchorElUser}
                                            open={Boolean(anchorElUser)}
                                            onClose={handleCloseUserMenu}
                                        >
                                            <MenuItem>
                                                <Button endIcon={<DeleteIcon />} onClick={(event) => { handleDeleteFolder(event, row); setFolderId(idx) }}>Delete</Button>
                                            </MenuItem>
                                        </Menu>
                                    </TableCell>
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