import React from 'react';
import {useRoutes} from 'hookrouter';
import Home from './pages/Home';
import NotFound from './pages/NotFound';
import Movie from './pages/Movie';


const routes = {
  '/': () => <Home />,
  '/movie/:id': ({id}) => <Movie id={id} />
};

const App = () => {
  const routeResult = useRoutes(routes);  
  return routeResult || <NotFound />;
}

export default App;
