export const loadPages = (currentPage, totalPages) => {
    const loadPages = [];
    const startIndex = Math.max(currentPage - 5, 1);
    const endIndex = Math.min(startIndex + 9, totalPages);

    if (startIndex > 1) {
        loadPages.unshift("Đầu");
    }

    for (let i = startIndex; i <= endIndex; i++) {
        loadPages.push(i);
    }

    if (endIndex < totalPages) {
        loadPages.push("Cuối");
    }
    
    return loadPages;
}