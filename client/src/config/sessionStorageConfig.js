const sessionStorageConfig = (key) => {
    if (!sessionStorage.getItem(key)) {
        sessionStorage.setItem(key, JSON.stringify({}));
    }

    let store = JSON.parse(sessionStorage.getItem(key));

    function save() {
        sessionStorage.setItem(key, JSON.stringify(store));
    }

    function get(key) {
        return store[key];
    }

    function set(key, value) {
        store[key] = value;
        save();
    }

    function remove(key) {
        return store[key];
    }

    return { get, set, remove }
}

export const getSessionStorage = (key) => {
    if (sessionStorage.getItem(key)) {
        return JSON.parse(sessionStorage.getItem(key));
    }

    return null;
}
export const removeSessionStorage = (key) => {
    sessionStorage.removeItem(key);
}

export default sessionStorageConfig;