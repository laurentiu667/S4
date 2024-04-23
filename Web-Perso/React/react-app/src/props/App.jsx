
import Student from './student.jsx';

function App() {

    return(
        <>
            <Student name="John" age="20" isStudent={true}/>
            <Student name="Jane" age="21" isStudent={false}/>
            <Student name="Joe" age="22" isStudent={true}/>
            <Student name="Darina" age={23} isStudent={false}/>
            <Student name="sadassdd"/>
        </>
        
    );
 
}

export default App
