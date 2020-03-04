import React from 'react'
import { render } from 'react-dom'
import { createStore } from 'redux'
import { Provider } from 'react-redux'
import App from './components/App'
import rootReducer from './reducers'
import {Waiter} from "react-wait";

const store = createStore(rootReducer);

render(
    <Provider store={store}>
        <Waiter>
            <App />
        </Waiter>
    </Provider>,
    document.getElementById('root')
);
