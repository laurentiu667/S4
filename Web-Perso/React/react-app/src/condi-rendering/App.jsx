function UserGreeting(props) {
    if (props.isLoggedIn) {
        return <h1>Welcome back, {props.username}!</h1>;
    } else {
        return <h1>Please sign in.</h1>;
    }
}

export default UserGreeting;