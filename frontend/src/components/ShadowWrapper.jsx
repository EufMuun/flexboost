import { useEffect, useRef } from "react";
import { createRoot } from "react-dom/client";
import "../css/tailwind.css";

const ShadowWrapper = ({ children }) => {
    const shadowRootRef = useRef(null);

    useEffect(() => {
        const shadowRoot = shadowRootRef.current.attachShadow({ mode: 'open' });

        // Создание нового div для рендера внутри Shadow DOM
        const div = document.createElement('div');
        shadowRoot.appendChild(div);

        // Создание стиля и добавление в Shadow DOM
        const style = document.createElement('style');
        style.textContent = `
            @import url('../css/tailwind.css');
        `;
        shadowRoot.appendChild(style);

        const root = createRoot(div);
        root.render(children);
    }, [children]);

    return <div ref={shadowRootRef} />;
};

export default ShadowWrapper;
