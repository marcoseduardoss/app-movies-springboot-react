import React from 'react';
import {useRoutes} from 'hookrouter';
import Home from './pages/Home';
import NotFound from './pages/NotFound';
import Movie from './pages/Movie';
import Admin from './pages/Admin';
import {isAuthenticated} from './Auth'


const publicRoutes = {
  '/': () => <Admin  />
};

const privateRoutes = {
  '/': () => <Home  />,
  '/movie/:id': ({id}) => <Movie id={id} />
};

const App = () => {
  //localStorage.setItem('user', 'marcos.eduardo');
  //console.log(isAuthenticated());
  const routeResult = useRoutes( isAuthenticated() ? privateRoutes : publicRoutes );  
  return routeResult || <NotFound />;
}

export default App;
/*
https://jasonwatmore.com/post/2019/04/06/react-jwt-authentication-tutorial-example
https://kentcdodds.com/blog/authentication-in-react-applications  
https://www.youtube.com/watch?v=sYe4r8WXGQg
import React from 'react'
import {useUser} from './context/auth'
const AuthenticatedApp = React.lazy(() => import('./authenticated-app'))
const UnauthenticatedApp = React.lazy(() => import('./unauthenticated-app'))
function App() {
  const user = useUser()
  return user ? <AuthenticatedApp /> : <UnauthenticatedApp />
}
export App*/
