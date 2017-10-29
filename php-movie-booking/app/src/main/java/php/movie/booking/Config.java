package php.movie.booking;

class Config {
    // URL到login.php文件
//    static final String LOGIN_URL = "http://192.168.1.52/lhu/login.php";
    static final String LOGIN_URL = "http://localhost:8086/login.php";

    // URL到register.php文件
//    static final String REGISTER_URL = "http://192.168.1.52/lhu/register.php";
    static final String REGISTER_URL = "http://localhost:8086/register.php";
    // URL到insert-ticket.php文件
//    static final String INSERT_TICKET_URL = "http://192.168.1.52/lhu/insert-ticket.php";
    static final String INSERT_TICKET_URL = "http://localhost:8086/insert-ticket.php";
    // URL到serach-ticket.php文件
//    static final String DATA_URL = "http://192.168.1.52/lhu/serach-ticket.php?username=";
    static final String DATA_URL = "http://localhost:8086/serach-ticket.php?username=";
    //在login.php的$ _POST ['key']中定義的帳號和密碼的密鑰
    static final String KEY_USERNAME = "username";
    static final String KEY_PASSWORD = "password";
    static final String KEY_MOVIENAME = "moviename";
    static final String KEY_RELEASEDATE = "releasedate";
    static final String KEY_EVENT = "event";
    static final String KEY_CATEGORY = "category";
    static final String KEY_SHEETS = "sheets";
    //如果Server等於succes
    static final String LOGIN_SUCCESS = "success";

    //鍵值 Sharedpreferences
    //選項 shared preferences
    static final String SHARED_PREF_NAME = "myloginapp";

    //這將用於存儲當前登錄用戶的帳號3
    static final String USERNAME_SHARED_PREF = "username";

    //我們將使用它來存boolean在sharedpreference追蹤用戶是否登錄
    static final String LOGGEDIN_SHARED_PREF = "loggedin";


    static final String JSON_ARRAY = "result";

    static final String MOVIE_1_INTRODUCTION="《星際過客》劇情敘述珍妮佛勞倫斯和克里斯普瑞特飾演兩名移民星艦的乘客，" +
            "前往另一個星球展開新生活，看似順遂的旅程遇上未知逆境：" +
            "他們的休眠艙竟在抵達前90年提早開啟。吉姆和奧蘿拉試圖解開背後的玄機，" +
            "兩人也在過程中無法自拔地愛上了彼此，完全無法抗拒對方強烈的吸引力...於此同時，" +
            "移民星艦開始漸漸崩解，他們將揭發提早甦醒的真相。";
    static final String MOVIE_2_INTRODUCTION="《決戰異世界：弒血之戰》延續吸血鬼與狼族存亡大戰，" +
            "莎倫娜（凱特貝琴薩 飾）領軍抵禦狼族強烈攻勢竟遭受同族背叛。" +
            "將與她僅存的盟友大衛（席歐詹姆斯 飾）和他的父親同時也是吸血鬼長老 托馬斯 （查爾斯丹斯 飾）攜手終止這場延燒百年的戰役，就算犧牲自我在所不惜！";
    static final String MOVIE_3_INTRODUCTION="《終極追殺令》一個12歲大漂亮無邪的小女孩瑪蒂達（娜塔莉.波曼飾），" +
            "在一家慘遭黑幫毒手後，央求殺手里昂（尚.雷諾飾）訓練她，讓她成為和他一樣的殺手，" +
            "以便為家人報仇。面對這個與自己本性極其矛盾的女孩，里昂試圖找到平衡的支點，" +
            "而總是與他處於對立狀態的瑪蒂達，也就成了里昂的唯一致命傷...";
}
