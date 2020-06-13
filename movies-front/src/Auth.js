export const isAuthenticated = () => {
    let user = localStorage.getItem('user');
    return  (user !== '') ? true : false;
};
