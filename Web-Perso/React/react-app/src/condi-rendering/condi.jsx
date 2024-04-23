
function UserGreeting(props){
    return(props.isLoggedIn ? <h1 className="logged">Welcome back, {props.username}!</h1> : <h1 className="notlogged">plzzz log in </h1>)
}

export default UserGreeting