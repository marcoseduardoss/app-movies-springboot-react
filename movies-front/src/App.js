import React from 'react';
import {useRoutes} from 'hookrouter';
import MoviesListPublicAccess from './pages/public/home/movie/List';
import MoviesListPrivateAccess from './pages/private/home/movie/List';
import MovieEdit from './pages/private/home/movie/Edit';
import MovieAdd from './pages/private/home/movie/Add';
import MovieDetails from './pages/public/home/movie/Detail';
import NotFound from './components/NotFound';
import About from './pages/public/About';
import {isAuthenticated} from './Auth'


const  privateRoutes = {
  '/about': () => <About  />,
  '/movie/edit/:id': ({id}) => <MovieEdit id={id} />,
  '/movie/add': () => <MovieAdd />,
  '/': () => <MoviesListPrivateAccess  />
};

const publicRoutes = {
  '/': () => <MoviesListPublicAccess  />,
  '/about': () => <About  />,
  '/movie/:id': ({id}) => <MovieDetails id={id} />
};

const App = () => {
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
