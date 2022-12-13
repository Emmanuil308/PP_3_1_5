
import {setInfoUserNavbarHeader} from "./aboutUser.js";
import {setInfoAboutUserTable} from "./aboutUser.js";

if (document.readyState === 'loading') {
    document.addEventListener('DOMContentLoaded', setInfoAboutUserTable);
    document.addEventListener('DOMContentLoaded', setInfoUserNavbarHeader);

} else {
    setInfoAboutUserTable();
    setInfoUserNavbarHeader();
}