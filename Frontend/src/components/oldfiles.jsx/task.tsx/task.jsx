// /*
// stefan Kiers
// 08 - 12 - '25
// tak page 
// */

// import { useQuery } from "@tanstack/react-query";
// import { Fragment, useState } from "react";
// import { API_URL } from "../App";
// import TopicDetail from "../components/topicDetail";

// const TaskPage = () => {

//   const [taskId, setTaskId] = useState(NaN);

//   const {
//     data: topics,
//     isLoading,
//     error,
//   } = useQuery({
//     queryKey: ["topics"],
//     queryFn: async () => {
//       const response = await fetch(`${API_URL}/topics`);
//       if (!response.ok) {
//         throw new Error("topic error");
//       }
//       return response.json();
//     },
//   });

//   if (isLoading) {
//     return <div>Loading...</div>;
//   }

//   if (error) {
//     return <div style={{ color: "red" }}>Error: {error.message}</div>;
//   }

//   if (topicId) {
//     return (
//       <>
//         <TopicDetail topicId={topicId} setTopicId={setTopicId} />
//       </>
//     );
//   }

//   return (
//     <div>
//       <h1>Topic pagina</h1>
//       {topics && topics.length > 0 ? (
//         <ul>
//           {topics.map((topic) => (
//             <Fragment key={topic.id}>
//               <li onClick={() => setTopicId(topic.id)}>
//                 <p>{topic.name}</p>
//               </li>
//               <br></br>
//             </Fragment>
//           ))}
//         </ul>
//       ) : (
//         <p>Geen topics gevonden</p>
//       )}
//     </div>
//   );
// };

// export default TopicPage;
