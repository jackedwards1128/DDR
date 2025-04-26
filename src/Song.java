import java.util.ArrayList;

// trap queen by fetty
// nokia by drake
// luther by kdot and sza

// hot to go by chappel
// see you again by tyler
// dark thoughts by tecca
// snow strippers remix ?

public class Song {
    private ArrayList<double[]> notes;
    private Game backend;
    private String music;
    private String name;
    private String mapString;

    private String trapQueen = "A409T2A448T2A476T1A501T1A527T0A549T3A575T2A598T0A623T3A649T3A668T1A672T0A691T3A717T2A739T0A765T3A788T1A811T0A833T3";
    private String nokia = "A303T3A306T0A354T2A402T1A453T0A479T3A501T0A543T2A571T1A619T2A641T3A689T0A734T1A785T2A835T3A877T1A925T2A973T3A1024T0A1047T2A1072T1A1114T0A1149T3A1168T2A1213T1A1261T0A1305T3A1353T0A1398T2A1449T3A1478T1A1500T2A1526T3A1548T0A1593T1A1641T2A1689T0A1740T0A1785T3A1831T3A1882T2A1927T1A1978T2A2022T3A2025T0A2070T0A2070T3A2214T2A2217T1A2262T2A2262T1A2402T2A2445T3A2547T0A2592T3A2637T2A2736T1A2783T0A2831T3A2924T2A2975T0A3019T3A3118T1A3163T2A3211T1A3259T2A3307T1A3361T0A3406T0A3453T3A3501T3A3542T2A3589T1A3618T0A3644T3A3669T2A3692T1A3734T0A3756T3A3772T1A3800T2A3835T0A3857T3A3864T2A3924T1A3969T0A3992T3A4017T2A4042T1A4071T0A4094T3A4119T2A4145T1A4170T0A4186T3A4212T2A4221T0A4250T1A4307T2A4352T1A4378T3A4403T0A4425T2A4445T1A4496T3A4547T0A4591T3A4642T0A4690T2A4738T2A4783T1A4824T1A4872T3A4901T0A4930T3A4958T0A4981T2A5022T1A5051T3A5080T3A5125T3A5170T3A5215T3A5237T0A5259T3A5288T2A5310T1A5333T0A5355T3A5378T2A5394T1A5426T0A5452T3A5474T2A5503T1A5525T0A5548T3A5573T0A5583T3A5613T2A5642T1A5667T0A5689T2A5712T1A5740T3A5762T3A5785T1A5813T2A5836T1A5858T3A5883T0A5906T2A5931T1A5953T0A5979T2A6020T1A6043T1A6071T0A6091T0A6116T2A6142T1A6167T3A6193T0A6212T2A6238T1A6260T0A6285T3A6307T3A6333T2A6358T1A6397T0A6445T3A6492T2A6518T1A6543T0A6569T3A6595T1A6620T2A6646T1A6674T2A6694T1A6719T2A6738T1A6764T2A6786T0A6837T0A6882T0A6930T3A6978T0A7022T3A7048T0A7096T3A7122T0A7172T3A7217T0A7239T3A7284T0A7310T3A7361T2A7402T2A7450T1A7496T1A7544T0A7576T3A7592T0A7618T3A7666T0A7688T3A7739T2A7784T2A7832T1A7877T1A7931T0A7972T3A8020T2A8068T1A8122T0A8122T3A8164T3A8167T0A8310T2A8314T1A8357T2A8360T1A8501T0A8545T3A8645T3A8693T0A8734T3A8837T2A8879T1A8927T2A9023T0A9071T3A9115T2A9211T1A9262T2A9300T3A9303T0A9358T2A9399T1A9447T3A9451T0A9498T1A9546T2A9587T3A9587T0A9648T3A9673T0A9702T2A9728T1A9757T3A9785T2A9814T0A9843T1A9871T3A9897T2A9926T0A9951T1A9983T3A10012T2A10037T0A10063T1A10095T3A10120T2A10149T0A10177T1A10209T3A10238T2A10263T0A10292T1A10323T3A10352T2A10378T0A10406T1A10435T3A10464T2A10492T3A10492T0A10553T3A10553T0A10610T0A10610T3A10665T3A10665T0A10722T3A10726T0A10777T2A10777T1A10831T2A10834T1A10888T2A10888T1A10946T2A10946T1A11003T3A11003T0A11061T3A11064T0A11115T0A11115T3A11166T3A11170T0A11227T1A11255T1A11284T3A11313T3A11341T0A11370T0A11399T0A11424T0A11453T3A11453T0A11482T3A11511T2A11546T1A11572T3A11597T0A11623T2A11649T1A11681T3A11706T2A11738T0A11764T1A11792T3A11821T0A11850T2A11875T1A11904T3A11936T0A11961T2A11987T1A12019T2A12048T0A12076T3A12102T1A12133T2A12162T0A12191T3A12216T1A12245T2A12277T2A12303T2A12331T2A12360T3A12360T0A12414T2A12443T2A12468T1A12497T1A12526T0A12554T3A12586T0A12612T2A12640T1A12669T3A12698T2A12726T0A12755T1A12784T2A12812T1A12841T3A12841T0A12866T3A12870T0A12898T3A12898T0A12933T2A12959T1A12984T3A13010T0A13039T2A13064T1A13093T3A13122T2A13148T1A13180T2A13205T1A13234T2A13266T0A13266T3A13320T3A13323T0A13352T3A13355T0A13403T3A13406T0A13431T3A13435T0A13492T0A13521T2A13547T1A13576T3A13604T2A13630T1A13662T0A13688T2A13716T1A13745T2A13771T1A13800T3A13832T0A13857T2A13886T1A13918T2A13943T3A13972T1A14001T0A14030T2A14055T1A14084T2A14113T1A14141T2A14170T0A14173T3A14228T3A14228T0A14253T1A14282T2A14311T0A14340T3A14368T1A14397T2A14423T3A14455T1A14483T0A14512T3A14534T2A14563T1A14595T0A14624T3A14652T2A14678T1A14710T0A14739T3A14764T1A14793T1A14821T2A14850T2A14879T0A14908T0A14933T3A14962T3A15022T3A15022T0A15080T3A15083T0A15112T2A15137T2A15163T1A15188T1A15217T2A15246T2A15274T1A15303T1A15361T0A15418T3A15473T2A15524T1A15581T0A15639T3A15700T2A15754T0A15869T3A15984T2A16038T1A16096T0A16150T3A16207T2A16236T2A16265T1A16290T1A16322T0A16345T0A16373T3A16399T3A16431T2A16459T1A16488T0A16514T3A16543T2A16572T1A16604T0A16629T2A16658T1A16689T0A16715T2A16746T1A16778T0A16807T3A16835T2A16861T1A16889T0A16947T3A17004T0A17058T3A17116T0A17171T3A17231T0A17286T3A17564T0A17628T0A17682T0A17710T0A17742T0A17797T3A17828T0A17854T2A17879T1A17908T3A17937T0A17966T2A17988T1A18020T3A18081T3A18167T3A18195T3A18253T0A18304T2A18365T1A18414T3A18474T0A18528T2A18586T1A18647T3A18647T0A18705T2A18727T2A18756T2A18781T2A18810T0A18838T3A18864T1A18899T2A18930T0A18952T3A18981T1A19007T2A19035T0A19064T3A19077T0A19096T3A19125T2A19150T1A19176T0A19204T3A19230T2A19259T1A19291T0A19319T2A19345T3A19374T1A19402T1A19434T0A19460T3A19486T2A19514T0A19543T1A19578T3A19604T0A19629T2A19658T1A19687T1A19715T0A19741T3A19770T2A19799T1A19834T0A19863T2A19888T3A19914T1A19942T0A19971T2A19997T3A20028T0A20064T2A20086T1A20115T0A20143T2A20172T3A20198T1A20223T3A20255T2A20287T0A20312T1A20341T3A20370T2A20396T0A20428T1A20453T3A20482T0A20511T2A20539T0A20552T3A20565T0A20597T3A20626T2A20651T1A20680T0A20709T3A20737T0A20769T3A20785T0A20798T3A20817T0A20881T2A20909T1A20935T3A20964T0A20992T2A21018T1A21047T3A21078T0A21104T2A21129T1A21161T3A21190T2A21219T0A21244T1A21273T2A21302T0A21330T1A21359T3A21359T0A21410T1A21439T2A21471T0A21474T3A21503T2A21525T1A21554T0A21554T3A21592T1A21614T2A21643T0A21697T3A21722T2A21751T1A21780T0A21808T2A21837T3A21866T1A21895T2A21920T3A21949T1A21981T2A22009T0A22035T1A22064T2A22092T3A22153T0A22179T3A22236T0A22262T3A22294T0A22319T1A22380T2A22403T0A22435T1A22460T3A22489T2A22518T0A22547T1A22604T3A22690T2A22722T0A22776T1A22830T3A22859T2A22888T0A22917T1A22946T3A22974T2A23000T0A23057T3A23163T1A23195T2A23284T0A23319T3A23344T1A23370T2A23398T0A23424T3A23459T0A23513T0A23627T0A23678T0A23739T0A23764T0A23793T3A23819T3A23850T1A23879T2A23905T0A23959T3A24128T3A24128T0";
    private String takeU = "A264T0A305T0A343T3A382T3A414T3A449T2A487T2A522T2A561T2A599T2A628T1A650T1A673T1A705T0A740T0A772T0A810T3A848T0A890T3A922T2A950T1A992T0A1030T3A1070T2A1101T0A1136T1A1165T3A1210T1A1248T2A1286T1A1321T3A1350T0A1375T0A1401T3A1433T2A1465T1A1500T3A1535T0A1580T2A1612T0A1647T1A1692T0A1714T3A1749T2A1771T3A1790T1A1806T0A1841T2A1867T3A1899T2A1940T0A1975T1A2010T3A2049T2A2078T1A2103T1A2129T0A2161T3A2193T2A2231T1A2260T2A2301T0A2349T3A2375T2A2417T1A2442T0A2484T3A2522T2A2557T1A2592T0A2628T3A2672T3A2708T3A2740T2A2778T2A2807T0A2829T0A2852T0A2887T3A2919T1A2951T2A3177T0A3177T3A3216T0A3219T3A3260T0A3264T3A3308T0A3312T3A3353T2A3398T1A3430T0A3468T3A3491T2A3513T1A3535T0A3561T3A3583T2A3606T1A3628T2A3650T3A3676T0A3698T1A3721T2A3740T0A3765T3A3784T1A3810T2A3832T3A3855T0A3877T2A3899T1A3944T0A3988T3A4033T2A4078T0A4078T3A4129T0A4132T3A4174T0A4174T3A4222T0A4222T3A4267T2A4267T1A4311T2A4311T1A4356T2A4356T1A4401T2A4404T1A4445T2A4493T1A4538T0A4585T3A4629T2A4674T0A4715T3A4760T1A4805T2A4830T0A4856T3A4881T2A4903T0A4929T1A4954T2A4973T3A4996T2A5040T0A5079T1A5127T3A5171T0A5222T0A5267T0A5312T0A5353T2A5401T3A5436T1A5490T2A5535T3A5583T3A5625T3A5676T3A5717T0A5740T0A5762T0A5797T2A5832T1A5873T2A5896T3A5940T0A5985T2A6029T1A6083T2A6128T2A6163T3A6202T3A6230T3A6262T0A6306T0A6344T0A6380T0A6418T0A6447T3A6469T3A6491T3A6523T1A6562T2A6593T0A6622T1A6670T2A6718T0A6759T3A6807T3A6807T0A6855T3A6858T0A6902T2A6947T2A6992T1A7037T1A7084T3A7084T0A7129T3A7129T0A7174T2A7193T0A7218T3A7257T2A7286T1A7315T2A7893T0A7985T3A8078T0A8126T0A8164T0A8199T0A8235T0A8263T3A8282T3A8308T3A8346T0A8382T0A8420T0A8445T2A8490T2A8532T2A8564T2A8627T1A8653T1A8672T1A8707T0A8743T0A8813T3A8854T3A8902T3A8947T3A8992T0A9040T0A9081T3A9125T3A9173T0A9215T0A9263T3A9307T3A9349T0A9444T3A9537T0A9626T3A9719T0A9812T3A9901T0A9946T0A9996T0A10038T0A10082T2A10127T2A10174T3A10219T3A10264T0A10305T0A10353T2A10392T2A10437T3A10468T0A10491T3A10513T0A10542T3A10577T3A10603T0A10647T3A10673T0A10698T3A10721T0A10756T0A10784T3A10829T2A10855T2A10880T0A10903T3A10938T0A10970T2A11014T3A11043T0A11078T3A11129T0A11171T2A11216T0A11263T3A11293T0A11321T3A11340T0A11360T3A11382T0A11402T3A11427T0A11449T3A11472T0A11491T3A11542T2A11565T2A11587T2A11622T0A11645T0A11673T3A11692T3A11718T0A11737T0A11769T3A11797T0A11832T3A11864T2A11896T0A11944T3A11991T0A12036T3A12078T2A12109T1A12129T2A12151T0A12173T3A12199T1A12221T2A12244T0A12266T3A12288T2A12310T0A12333T3A12358T2A12381T1A12406T2A12425T0A12444T3A12470T2A12492T1A12514T0A12540T3A12565T1A12590T1A12610T2A12629T0A12651T1A12673T3A12699T1A12718T0A12737T2A12763T1A12788T3A12827T2A12854T0A12870T1A12895T2A12924T3A12949T0A12990T3A13012T3A13041T0A13073T2A13105T0A13134T3A13172T2A13220T0A13268T0A13312T0A13357T3A13405T0A13450T2A13494T1A13536T0A13574T3A13619T2A13660T1A13711T2A13759T0A13801T3A13845T1A13897T2A13948T2A13993T0A14034T0A14082T3A14125T2A14160T3A14195T1A14230T2A14259T0A14308T0A14343T3A14381T3A14419T2A14445T1A14470T2A14493T0A14525T3A14560T2A14598T1A14627T2A14675T2A14710T0A14745T0A14806T3A14854T3A14892T2A14924T2A14959T0A14991T0A14994T3A15032T3A15036T0A15071T3A15074T0A15109T0A15109T3A15148T0A15148T3A15173T2A15196T2A15215T2A15247T1A15282T3A15320T0A15349T2A15397T1A15432T0A15471T2A15509T3A15534T0A15582T0A15617T2A15617T1A15653T2A15653T1A15688T2A15688T1A15717T0A15765T0A15800T3A15835T3A15870T3A15899T2A15921T1A15946T2A15978T0A16013T3A16049T2";

    private String takeU_unce= "U269U310U351U386U413U450U493U531U564U599U630U655U678U710U746U780U812U858U897U930U965U997U1040U1079U1112U1149U1175U1221U1260U1295U1333U1360U1383U1403U1440U1475U1510U1541U1584U1621U1656U1696U1721U1817U1908U1953U2001U2044U2087U2178U2269U2317U2363U2409U2452U2550U2634U2681U2725U2770U2817U2863U2906U2952U3180U3224U3268U3313U3356U3405U3453U3496U3541U3587U3633U3678U3727U3771U3816U3862U3905U3953U3997U4047U4088U4134U4180U4225U4269U4315U4361U4407U4451U4495U4543U4590U4633U4676U4725U4773U4816U4861U4907U4952U4998U5042U5088U5137U5179U5224U5271U5314U5362U5406U5447U5499U5549U5590U5633U5681U5722U5772U5815U5859U5901U5949U5997U6041U6086U6133U6182U6232U6274U6318U6359U6402U6452U6474U6496U6539U6589U6630U6678U6725U6773U6814U6836U6860U6907U6954U6994U7042U7090U7133U7175U7197U7224U7270U7318U8266U8448U8627U8814U9359U9453U9544U9587U9634U9726U9818U9908U9952U9999U10043U10087U10135U10181U10227U10269U10312U10360U10408U10456U10477U10498U10518U10542U10577U10607U10723U10811U10905U11279U11316U11361U11381U11405U11428U11450U11472U11498U11518U11542U11562U11579U11593U11606U11622U11636U11652U11667U11681U11694U11708U11723U11737U11750U11904U11952U11999U12045U12086U12133U12181U12224U12267U12313U12364U12410U12450U12496U12543U12587U12632U12678U12725U12771U12815U12860U12905U12951U12995U13042U13088U13133U13177U13224U13270U13314U13359U13454U13499U13541U13588U13636U13680U13725U13770U13816U13859U13904U13952U13996U14041U14088U14134U14178U14224U14269U14315U14359U14406U14449U14498U14545U14585U14630U14679U14725U14770U14815U14861U14906U14951U14994U15044U15091U15135U15179U15223U15269U15316U15359U15407U15454U15498U15541U15584U15634U15680U15725U15771U15814U15859U15903U15951U16001U16045U";

    public Song(Game backend, String music, String name) {
        this.notes = new ArrayList<double[]>();
        this.backend = backend;
        this.music = music;
        this.name = name;
        this.mapString = "";

        loadSongMapping(name);
    }

    public double[] get(int index) {
        return notes.get(index);
    }

    public int getSize() {
        return notes.size();
    }

    public String getMusic() {
        return music;
    }

    public String getName() {
        return name;
    }

    public void loadSongMapping(String song) {
        switch (song) {
            case "trapqueen":
                mapString = trapQueen;
                break;
            case "nokia":
                mapString = nokia;
                break;
            case "takeu":
                mapString = takeU;
                break;
        }
    }

    public boolean parseMapString() {
        if (mapString.indexOf("A") == -1) {
            return false;
        }
        mapString = mapString.substring(1);
        double time = Float.parseFloat(mapString.substring(0,mapString.indexOf("T"))) / 100;
        mapString = mapString.substring(mapString.indexOf("T")+1);
        int dir = Integer.parseInt(mapString.substring(0,1));
        mapString = mapString.substring(1);

        backend.addArrow(time, dir);

        return true;
    }

    public void clearMapString() {
        mapString = "";
    }

    public String getUnceString() {
        return takeU_unce;
    }

    public void trapQueen() {

        double startOffset = 0.0;
        double noteOffset = 0.00;

        double BPMstep = 60.0 / 148;
        double startingDing = 0.608 + noteOffset;
        double startingDing2 = 0.810 + noteOffset;


        notes.add(new double[]{3.72 + startOffset, 0});
        notes.add(new double[]{0, 3});
        notes.add(new double[]{startingDing, 2});
        notes.add(new double[]{startingDing, 0});
        notes.add(new double[]{startingDing2, 3});
        notes.add(new double[]{startingDing, 3});
        notes.add(new double[]{startingDing,1});
        notes.add(new double[]{startingDing,2});
        notes.add(new double[]{startingDing, 0});
        notes.add(new double[]{0, 3});
        notes.add(new double[]{startingDing2,2});
        notes.add(new double[]{startingDing, 0});
        notes.add(new double[]{startingDing,1});
        notes.add(new double[]{startingDing,1});
        notes.add(new double[]{startingDing, 1});
        notes.add(new double[]{0, 2});
        notes.add(new double[]{startingDing2,3});
        notes.add(new double[]{startingDing, 0});
        notes.add(new double[]{startingDing,2});
        notes.add(new double[]{startingDing,1});
        notes.add(new double[]{startingDing, 0});
        notes.add(new double[]{0, 2});
        notes.add(new double[]{startingDing2,3});
        notes.add(new double[]{startingDing, 2});
        notes.add(new double[]{startingDing,1});
        notes.add(new double[]{startingDing,2});
        notes.add(new double[]{startingDing, 0});
        notes.add(new double[]{startingDing2-0.2,0});
        notes.add(new double[]{0, 3});
        notes.add(new double[]{startingDing, 0});
        notes.add(new double[]{0, 3});
        notes.add(new double[]{startingDing, 0});
        notes.add(new double[]{0, 3});
        notes.add(new double[]{startingDing, 1});
        notes.add(new double[]{0, 2});
        notes.add(new double[]{startingDing, 0});
        notes.add(new double[]{0, 3});
        notes.add(new double[]{startingDing2,2});
        notes.add(new double[]{startingDing, 2});
        notes.add(new double[]{startingDing, 0});
        notes.add(new double[]{startingDing, 0});
        notes.add(new double[]{startingDing, 1});
        notes.add(new double[]{0, 3});
        notes.add(new double[]{startingDing2,3});
        notes.add(new double[]{startingDing, 0});
        notes.add(new double[]{startingDing,2});
        notes.add(new double[]{startingDing,1});
        notes.add(new double[]{startingDing, 0});
        notes.add(new double[]{0, 2});
        notes.add(new double[]{startingDing2,1});
        notes.add(new double[]{0, 0});
        notes.add(new double[]{startingDing, 1});
        notes.add(new double[]{0, 2});
        notes.add(new double[]{startingDing,1});
        notes.add(new double[]{0, 0});
        notes.add(new double[]{startingDing,1});
        notes.add(new double[]{0, 2});
        notes.add(new double[]{startingDing, 0});
        notes.add(new double[]{0, 3});
        notes.add(new double[]{startingDing2,1});

        notes.add(new double[]{BPMstep, 0});
        notes.add(new double[]{BPMstep, 0});
        notes.add(new double[]{BPMstep, 0});
        notes.add(new double[]{BPMstep, 0});

        notes.add(new double[]{BPMstep, 3});
        notes.add(new double[]{BPMstep, 3});
        notes.add(new double[]{BPMstep, 3});
        notes.add(new double[]{BPMstep, 3});

        notes.add(new double[]{BPMstep, 0});
        notes.add(new double[]{BPMstep, 3});
        notes.add(new double[]{BPMstep, 0});
        notes.add(new double[]{BPMstep, 3});

        notes.add(new double[]{BPMstep, 0});
        notes.add(new double[]{0, 3});
        notes.add(new double[]{BPMstep, 0});
        notes.add(new double[]{0, 3});
        notes.add(new double[]{BPMstep, 0});
        notes.add(new double[]{0, 3});
        notes.add(new double[]{BPMstep, 0});
        notes.add(new double[]{0, 3});

        notes.add(new double[]{startingDing, 2});
        notes.add(new double[]{0, 1});
        notes.add(new double[]{startingDing,2});
        notes.add(new double[]{0, 3});
        notes.add(new double[]{startingDing,2});
        notes.add(new double[]{0, 1});
        notes.add(new double[]{startingDing, 2});
        notes.add(new double[]{0, 3});
        notes.add(new double[]{startingDing2,1});
        notes.add(new double[]{startingDing, 0});
        notes.add(new double[]{startingDing,1});
        notes.add(new double[]{startingDing,1});
        notes.add(new double[]{startingDing, 1});
        notes.add(new double[]{0, 2});
        notes.add(new double[]{startingDing2,2});

        notes.add(new double[]{startingDing2,2});
        notes.add(new double[]{startingDing, 2});
        notes.add(new double[]{startingDing, 0});
        notes.add(new double[]{startingDing, 0});
        notes.add(new double[]{startingDing, 1});

        notes.add(new double[]{startingDing2,3});
        notes.add(new double[]{startingDing, 0});
        notes.add(new double[]{startingDing, 1});
        notes.add(new double[]{startingDing, 2});
        notes.add(new double[]{startingDing, 1});

        notes.add(new double[]{BPMstep, 3});
        notes.add(new double[]{BPMstep, 3});
        notes.add(new double[]{BPMstep, 3});
        notes.add(new double[]{BPMstep, 3});

        notes.add(new double[]{BPMstep/2, 3});
        notes.add(new double[]{BPMstep/2, 3});
        notes.add(new double[]{BPMstep/2, 3});
        notes.add(new double[]{BPMstep/2, 3});
        notes.add(new double[]{BPMstep/2, 3});
        notes.add(new double[]{BPMstep/2, 3});
        notes.add(new double[]{BPMstep/2, 3});
        notes.add(new double[]{BPMstep/2, 3});

        notes.add(new double[]{(BPMstep*4)-0.4, 0});
        notes.add(new double[]{0, 3});
        notes.add(new double[]{BPMstep*2, 0});
        notes.add(new double[]{BPMstep/2, 0});
        notes.add(new double[]{BPMstep/2, 1});
        notes.add(new double[]{BPMstep/2, 1});
        notes.add(new double[]{BPMstep/2, 2});
        notes.add(new double[]{BPMstep/2, 2});
        notes.add(new double[]{BPMstep/2, 3});
        notes.add(new double[]{BPMstep/2, 3});
        notes.add(new double[]{BPMstep/2, 0});
        notes.add(new double[]{BPMstep/2, 1});
        notes.add(new double[]{BPMstep/2, 0});
        notes.add(new double[]{BPMstep/2, 1});

        notes.add(new double[]{startingDing2-1.1,2});
        notes.add(new double[]{startingDing, 1});
        notes.add(new double[]{startingDing, 0});
        notes.add(new double[]{startingDing, 0});
        notes.add(new double[]{startingDing, 3});

        notes.add(new double[]{startingDing2,1});
        notes.add(new double[]{startingDing, 0});
        notes.add(new double[]{0, 3});
        notes.add(new double[]{startingDing, 3});
        notes.add(new double[]{startingDing, 1});
        notes.add(new double[]{startingDing, 0});

        notes.add(new double[]{startingDing2,2});
        notes.add(new double[]{startingDing, 1});
        notes.add(new double[]{startingDing, 0});
        notes.add(new double[]{startingDing, 0});
        notes.add(new double[]{startingDing, 3});

        notes.add(new double[]{startingDing2,1});
        notes.add(new double[]{startingDing, 2});
        notes.add(new double[]{startingDing, 1});
        notes.add(new double[]{startingDing, 2});
        notes.add(new double[]{startingDing, 1});

        notes.add(new double[]{BPMstep, 0});
        notes.add(new double[]{BPMstep, 1});
        notes.add(new double[]{BPMstep, 2});
        notes.add(new double[]{BPMstep, 3});
        notes.add(new double[]{BPMstep, 3});
        notes.add(new double[]{BPMstep, 2});
        notes.add(new double[]{BPMstep, 1});
        notes.add(new double[]{BPMstep, 0});
    }

    public void nokia() {
        double startOffset = 3.0;
        double noteOffset = 0.0;

        double BPMstep = (60.0 / 126) - 0.003;
        double startingDing = 0.608 + noteOffset;
        double startingDing2 = 0.810 + noteOffset;

        notes.add(new double[]{startOffset, 0});
        notes.add(new double[]{BPMstep, 0});
        notes.add(new double[]{BPMstep, 0});
        notes.add(new double[]{BPMstep, 0});
        notes.add(new double[]{BPMstep, 0});
        notes.add(new double[]{BPMstep/2, 3});
        //
        notes.add(new double[]{BPMstep-0.07, 0});
        notes.add(new double[]{BPMstep, 2});
        notes.add(new double[]{0, 0});
        notes.add(new double[]{BPMstep, 1});
        notes.add(new double[]{BPMstep, 3});

        notes.add(new double[]{BPMstep, 3});
        notes.add(new double[]{BPMstep/3, 2});
        notes.add(new double[]{BPMstep/3, 2});
        notes.add(new double[]{BPMstep/3, 2});
        notes.add(new double[]{BPMstep, 1});
        notes.add(new double[]{BPMstep, 0});

        notes.add(new double[]{BPMstep, 0});
        notes.add(new double[]{BPMstep, 3});
        notes.add(new double[]{BPMstep, 1});
        notes.add(new double[]{0, 3});
        notes.add(new double[]{BPMstep, 3});

        notes.add(new double[]{BPMstep, 2});
        notes.add(new double[]{BPMstep, 2});
        notes.add(new double[]{BPMstep, 0});
        notes.add(new double[]{BPMstep/2, 2});
        notes.add(new double[]{BPMstep/2, 0});

        notes.add(new double[]{BPMstep, 0});
        notes.add(new double[]{BPMstep/2, 3});
        notes.add(new double[]{BPMstep, 0});
        //notes.add(new double[]{BPMstep, 0});
        notes.add(new double[]{BPMstep/2, 1});

        notes.add(new double[]{BPMstep*4, 0});
        notes.add(new double[]{BPMstep*4, 3});

        notes.add(new double[]{BPMstep, 1});
        notes.add(new double[]{0, 2});
        notes.add(new double[]{BPMstep, 1});
        notes.add(new double[]{0, 2});
        notes.add(new double[]{BPMstep, 1});
        notes.add(new double[]{0, 2});
        notes.add(new double[]{BPMstep, 1});
        notes.add(new double[]{0, 2});

        notes.add(new double[]{BPMstep, 0});
        notes.add(new double[]{0, 3});
        notes.add(new double[]{BPMstep*4, 0});
        notes.add(new double[]{0, 3});

        notes.add(new double[]{BPMstep*4, 1});
        notes.add(new double[]{BPMstep, 2});

        notes.add(new double[]{BPMstep*3, 0});
        notes.add(new double[]{BPMstep, 3});

        notes.add(new double[]{BPMstep*3, 3});
        notes.add(new double[]{BPMstep, 0});

        notes.add(new double[]{BPMstep*3, 1});
        notes.add(new double[]{BPMstep, 2});

        notes.add(new double[]{BPMstep*3, 1});
        notes.add(new double[]{BPMstep, 1});
        notes.add(new double[]{BPMstep, 1});
        notes.add(new double[]{BPMstep, 1});
        notes.add(new double[]{0, 3});
        notes.add(new double[]{BPMstep*2/3, 0});
        notes.add(new double[]{0, 3});
        notes.add(new double[]{BPMstep*2/3, 0});
        notes.add(new double[]{0, 3});
        notes.add(new double[]{BPMstep*2/3, 0});
        notes.add(new double[]{0, 3});
        notes.add(new double[]{BPMstep*2/3, 0});
        notes.add(new double[]{0, 3});
        notes.add(new double[]{BPMstep*2/3, 0});
        notes.add(new double[]{0, 3});
        notes.add(new double[]{BPMstep*2/3, 0});
        notes.add(new double[]{0, 3});

        notes.add(new double[]{BPMstep*2, 0});
        notes.add(new double[]{BPMstep, 1});
        notes.add(new double[]{0, 3});
        notes.add(new double[]{BPMstep, 3});

        notes.add(new double[]{BPMstep, 2});
        notes.add(new double[]{BPMstep, 2});
        notes.add(new double[]{BPMstep, 0});
        notes.add(new double[]{BPMstep/2, 2});
        notes.add(new double[]{BPMstep/2, 0});
    }
}
