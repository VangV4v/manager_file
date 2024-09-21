import { Alert, Box, Button, Container, Dialog, DialogActions, DialogContent, DialogTitle, IconButton, Menu, MenuItem, TextField, Typography } from "@mui/material";
import AddIcon from '@mui/icons-material/Add';
import { useEffect, useState } from "react";
import folderAPI from "../../api/folder-api";
import { useSelector } from "react-redux";
import { useForm } from "react-hook-form";
import CheckIcon from '@mui/icons-material/Check';
import DensityMediumIcon from '@mui/icons-material/DensityMedium';
import DeleteIcon from '@mui/icons-material/Delete';
import Swal from "sweetalert2";
import { DataGrid, useGridApiRef } from "@mui/x-data-grid";
import EditIcon from '@mui/icons-material/Edit';
import { MESS_SUCCESS_CREATE_FOLDER, MESS_SUCCESS_UPDATE_FOLDER } from "../../contants/contant";

function HomePage() {

    const [notifyMess, setNotifyMess] = useState();
    const [folderData, setFolderData] = useState([]);
    const jwt = useSelector(state => state.authUserReducer.authUser.jwt);
    const [isShowNotify, setShowNotify] = useState(false);
    const [anchorElUser, setAnchorElUser] = useState(null);
    const apiRef = useGridApiRef();
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
    const [openEdit, setOpenEdit] = useState(false);
    const handleClickOpen = () => {
        setOpen(true);
    };
    const handleClose = () => {
        setOpen(false);
    };
    const { register, handleSubmit } = useForm();
    const handleCreateFolder = (data) => {

        folderAPI.createFolderByUser(jwt, data).then(response => {

            console.log(response);
            const cloneFolderData = [...folderData];
            const dataResponse = {
                ...response.data.folderData,
                id: cloneFolderData.length + 1
            };
            cloneFolderData.push(dataResponse);
            setFolderData(cloneFolderData);
            setNotifyMess(MESS_SUCCESS_CREATE_FOLDER);
            displayNotification();
        }).catch(err => {

            console.log(err);
        });
        handleClose();
    };
    //end dialog
    //delete
    const handleDeleteFolder = () => {

        const data = apiRef.current.getSelectedRows().values().next().value;
        const dataAfterUpdate = {
            ...data,
            status: 0
        };
        setAnchorElUser(null);
        Swal.fire({
            title: "Are you sure?",
            text: "We switch your folder to trash!",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "Yes, delete it!"
        }).then((result) => {
            if (result.isConfirmed) {

                folderAPI.updateFolderByUser(jwt, dataAfterUpdate).then(response => {
                    Swal.fire({
                        title: "Deleted!",
                        text: "Your folder has been deleted.",
                        icon: "success"
                    });
                    const newData = folderData.filter(item => (item.folderId != dataAfterUpdate.folderId));
                    setFolderData(newData);
                }).catch(err => {
                    console.log(err);
                });
            }
        });
    };
    //data gird
    const handleClickAction = () => {
        setOpenEdit(true);
        setAnchorElUser(null);
    };
    const columns = [
        { field: 'id', headerName: 'No', hide: true },
        {
            field: 'folderName',
            headerName: 'Folder name',
            width: 400,
        },
        {
            field: 'fileInFolder',
            headerName: 'Count of file',
            width: 300,
        },
        {
            field: 'createdDate',
            headerName: 'Create date',
            width: 350,
        },
        {
            field: 'action',
            headerName: 'Action',
            width: 300,
            renderCell: () => (
                <Box>
                    <IconButton onClick={handleOpenUserMenu}><DensityMediumIcon /></IconButton>
                    <Menu
                        anchorEl={anchorElUser}
                        open={Boolean(anchorElUser)}
                        onClose={handleCloseUserMenu}
                    >
                        <MenuItem sx={{ width: '300px' }}>
                            <Button sx={{ flexGrow: 1 }} endIcon={<DeleteIcon />} onClick={handleDeleteFolder}>Delete</Button>
                        </MenuItem>
                        <MenuItem>
                            <Button sx={{ flexGrow: 1 }} endIcon={<EditIcon />} onClick={handleClickAction}>Rename</Button>
                        </MenuItem>
                    </Menu>
                </Box>
            )
        },
    ];
    //edit folder
    const handleUpdateNameFolder = (data) => {

        const currentData = apiRef.current.getSelectedRows().values().next().value;
        const renameData = {
            ...currentData,
            folderName: data.folderName,
        };
        folderAPI.updateFolderByUser(jwt, renameData).then(response => {

            const newFolderData = [...folderData];
            newFolderData.filter(item => {

                if (item.folderId === response.data.folderData.folderId) {
                    item.folderName = response.data.folderData.folderName;
                }
            });
            setFolderData(newFolderData);
            setOpenEdit(false);
            setNotifyMess(MESS_SUCCESS_UPDATE_FOLDER);
            displayNotification();
        }).catch(err => {
            console.log(err);
        });
    };
    useEffect(() => {

        const loadFolderData = async () => {

            await folderAPI.getFoldersByUser(jwt).then(response => {

                const cloneData = Array.from(response.data).map((data, idx) => (
                    {
                        ...data,
                        id: idx + 1
                    }
                ));
                setFolderData(cloneData);
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
                        {notifyMess}
                    </Alert>
                }
            </Box>
            <Box sx={{ pt: 3 }}>
                <DataGrid
                    columnVisibilityModel={{
                    }}
                    apiRef={apiRef}
                    rows={folderData}
                    columns={columns}
                    onRowDoubleClick={(item) => {
                        console.log(apiRef.current.getSelectedRows().entries().next().value);
                    }}
                />
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
                <form onSubmit={handleSubmit(handleUpdateNameFolder)}>
                    <Dialog
                        open={openEdit}
                        onClose={handleClose}
                        PaperProps={{
                            component: 'form',

                        }}
                    >
                        <DialogTitle>Rename Folder</DialogTitle>
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
                            <Button variant="contained" onClick={() => { setOpenEdit(false); }}>CLOSE</Button>
                            <Button variant="contained" type="submit">Save</Button>
                        </DialogActions>
                    </Dialog>
                </form>
            </Box>
        </Container>
    );
}

export default HomePage;