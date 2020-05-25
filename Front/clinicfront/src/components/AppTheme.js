import { createMuiTheme } from '@material-ui/core/styles';

const AppTheme = createMuiTheme({
    palette: {
        primary: {
            main: '#4d1919'
        }
    },
    status: {
        danger: "red",
    },
});

export default AppTheme