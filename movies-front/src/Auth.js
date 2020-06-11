export const isAuthenticated = () => {
    let user = localStorage.getItem('user');
    return  user!=null && user !='';
};
