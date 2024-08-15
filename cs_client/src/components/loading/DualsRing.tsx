import React from 'react'

interface PropsType {
    isLoading: boolean,
}

const Loading = ({ isLoading }: PropsType) => {
    return (
        <>
            {isLoading &&
                <div className='loading-container'>
                    <div className="lds-dual-ring"></div>
                </div>
            }
        </>
    )
}

export default Loading