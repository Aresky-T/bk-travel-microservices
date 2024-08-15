export function randomColor(): string {
    const rColor = Math.floor(Math.random() * 256);
    const gColor = Math.floor(Math.random() * 256);
    const bColor = Math.floor(Math.random() * 256);
    return `#${rColor.toString(16)}${gColor.toString(16)}${bColor.toString(16)}`
}