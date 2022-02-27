import http from "api";
import PresenteCard from "components/PresenteCard";
import { useEffect, useState } from "react";
import { PresentesPage } from "types/casa";


function Listing() {

    const [page, setPage] = useState<PresentesPage>();


    useEffect(() => {
        http.get('/presentes/page').then(res => {
            const data = res.data as PresentesPage;
            setPage(data);

        })
    },[])


    return (
        <>
               <div className="container">
                <div className="row">
                    {page?.content?.map(item => (
                        <div key={item.id} className="col-sm-6 col-lg-4 col-xl-3 mb-3">
                           <PresenteCard presente={item}/> 
                        </div>
                    )
                    )}
                </div>
            </div>
        </>
    );
}

export default Listing;