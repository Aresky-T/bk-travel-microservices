class MyStorage {
    private _name: string;
    private _data: any;

    public constructor(name: string) {
        this._name = name;
        this._data = JSON.parse(localStorage.getItem(name) ?? "null");
    }

    public set setData(data: any) {
        this._data = data;
        this.save();
    }

    public get getData() {
        return this._data;
    }

    public removeData(){
        localStorage.removeItem(this._name);
    }

    public setItem(key: string, value: any) {
        this._data = { ...this._data, [key]: value };
        this.save();
    }

    public getItem(key: string) {
        if (this._data) {
            return this._data[key];
        }
        return null;
    }

    public removeItem(key: string) {
        delete this._data[key];
        this.save();
    }

    private save() {
        localStorage.setItem(this._name, JSON.stringify(this._data));
    }
}

export function localStorageConfig(name: string) {
    return new MyStorage(name);
}
