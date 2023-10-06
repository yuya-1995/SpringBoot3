import React, {useState} from "react";
import useSWR from 'swr';
import './App.css';

function App(){
	const baseUrl = "http://localhost:8080/post";
	const fetcher = (url)=>fetch(url).then(res => res.json());
	const [url, setUrl] = useState(baseUrl);
	const {data} = useSWR(url, fetcher);
//	const [data,setData] = useState("");
	
	const updateUrl = (e)=> {
		setUrl(baseUrl + '/' + e.target.value);
	}
	
//	async function getData(){
//		const response = await fetch(url);
//		const data = await response.json();
//		const str = JSON.stringify(data,null,' ');
//		setData(str);
//	}
	
//	getData();
	
	return (
		<div className="App">
		<h1>React app.</h1>
		<input type="number" onChange={updateUrl} />
			<pre>
				<ul>
					<li>ID: {data ? data.id : ''}</li>
					<li>USERID: {data ? data.userId : ''}</li>
					<li>TITLE: {data ? data.title : ''}</li>
					<li>BODY: {data ? data.body : ''}</li>
				</ul>
			</pre>
		</div>
	);
	
}

export default App;