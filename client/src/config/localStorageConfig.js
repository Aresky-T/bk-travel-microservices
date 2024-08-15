/**
 * The function creates a custom storage object that allows for setting, getting, and removing values
 * from local storage.
 * @param key - The key parameter is a string that is used as the identifier for the custom storage
 * object. It is used to retrieve the stored data from the localStorage.
 * @returns An object with three methods: `set`, `get`, and `remove`.
 */
export const createCustomStorage = function (key) {
    if (!localStorage.getItem(key)) {
        localStorage.setItem(key, JSON.stringify({}));
    }

    let appStorage = JSON.parse(localStorage.getItem(key));

    function save() {
        localStorage.setItem(key, JSON.stringify(appStorage));
    }

    /**
     * The function sets a value for a given key in appStorage and saves the changes.
     * @param key - The key is a string that represents the name of the data that you want to store in
     * the appStorage object. It is used to identify the data when you want to retrieve it later.
     * @param value - The value to be stored in the appStorage object with the specified key.
     */
    function set(key, value) {
        appStorage[key] = value;
        save();
    }

    /**
     * The function "get" retrieves a value from the appStorage object using a specified key.
     * @param key - The key is a string that represents the name of the property in the appStorage
     * object that we want to retrieve the value of. The function returns the value associated with the
     * specified key.
     * @returns the value of the property with the specified key from the appStorage object.
     */
    function get(key) {
        return appStorage[key];
    }

    function remove(key) {
        delete appStorage[key];
        save();
    }

    return { set, get, remove }
}

/**
 * This function retrieves a value from local storage and parses it as JSON.
 * @param key - The key parameter is a string that represents the key of the item to be retrieved from
 * the localStorage. It is used to identify the item that was previously stored in the localStorage.
 * @returns The function `getLocalStorage` is returning the parsed value of the item with the specified
 * key from the browser's local storage. The value is returned as a JavaScript object.
 */
export const getLocalStorage = function (key) {
    return JSON.parse(localStorage.getItem(key));
}

/**
 * It removes the value of the key from the localStorage.
 * @param key - The key of the item you want to remove from localStorage.
 */
export const removeLocalStorage = function (key) {
    localStorage.removeItem(key);
}