import { render, screen } from '@testing-library/react';
import App from './App';
import React from 'react';
import "@testing-library/jest-dom/extend-expect";


// Mock the getUserInfo function from './services/registerAPI'
jest.mock('./services/registerAPI', () => ({
  getUserInfo: jest.fn(() => null),
}));

describe('App component', () => {
  beforeEach(() => {
    // Clear any previous mocks and reset the logged-in state
    jest.clearAllMocks();
  });

  test('renders SignInRegisterContainer when not logged in', () => {
    render(<App />);
    const signInRegisterContainerElement = screen.getByText("connects.");
    expect(signInRegisterContainerElement).toBeInTheDocument();
  });

});
