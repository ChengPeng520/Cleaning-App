package com.example.cleaningapp.customer.csCreateOrder

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.model.Coupon
import com.example.cleaningapp.databinding.FragmentCsCreateOrderBinding
import java.util.*
import kotlin.math.roundToInt

class CsCreateOrderFragment : Fragment() {
    private lateinit var binding: FragmentCsCreateOrderBinding
    private val viewModel: CsCreateOrderViewModel by viewModels()
    private val countyList = arrayOf(
        "基隆市", "臺北市", "新北市",
        "桃園市", "新竹市", "新竹縣",
        "苗栗縣", "臺中市", "彰化縣",
        "南投縣", "雲林縣", "嘉義市",
        "嘉義縣", "臺南市", "高雄市",
        "屏東縣", "臺東縣", "花蓮縣",
        "宜蘭縣", "澎湖縣", "金門縣",
        "連江縣"
    )
    private val districtMap = mapOf(
        "基隆市" to arrayOf(
            "中正區",
            "七堵區",
            "暖暖區",
            "信義區",
            "仁愛區",
            "中山區",
            "安樂區"
        ),
        "臺北市" to arrayOf(
            "北投區",
            "中山區",
            "信義區",
            "士林區",
            "大安區",
            "大同區",
            "文山區",
            "萬華區",
            "南港區",
            "松山區",
            "中正區",
            "內湖區"
        ),
        "新北市" to arrayOf(
            "三峽區",
            "淡水區",
            "新店區",
            "三重區",
            "板橋區",
            "汐止區",
            "新莊區",
            "中和區",
            "林口區",
            "石門區",
            "雙溪區",
            "金山區",
            "永和區",
            "五股區",
            "樹林區",
            "泰山區",
            "土城區",
            "鶯歌區",
            "蘆洲區",
            "瑞芳區",
            "三芝區",
            "石碇區",
            "八里區",
            "深坑區",
            "坪林區",
            "平溪區",
            "貢寮區",
            "萬里區",
            "烏來區"
        ),
        "桃園市" to arrayOf(
            "龜山區",
            "八德區",
            "蘆竹區",
            "大園區",
            "新屋區",
            "觀音區",
            "龍潭區",
            "平鎮區",
            "中壢區",
            "桃園區",
            "楊梅區",
            "大溪區",
            "復興區"
        ),
        "新竹市" to arrayOf(
            "東區",
            "北區",
            "香山區"
        ),
        "新竹縣" to arrayOf(
            "新豐鄉",
            "芎林鄉",
            "竹北市",
            "湖口鄉",
            "關西鎮",
            "新埔鎮",
            "竹東鎮",
            "尖石鄉",
            "寶山鄉",
            "北埔鄉",
            "橫山鄉",
            "峨眉鄉",
            "五峰鄉"
        ),
        "苗栗縣" to arrayOf(
            "苗栗市",
            "卓蘭鎮",
            "大湖鄉",
            "公館鄉",
            "銅鑼鄉",
            "頭屋鄉",
            "三義鄉",
            "三灣鄉",
            "竹南鎮",
            "頭份市",
            "後龍鎮",
            "苑裡鎮",
            "通霄鎮",
            "西湖鄉",
            "南庄鄉",
            "造橋鄉",
            "獅潭鄉",
            "泰安鄉"
        ),
        "臺中市" to arrayOf(
            "梧棲區",
            "東勢區",
            "大甲區",
            "豐原區",
            "太平區",
            "大里區",
            "龍井區",
            "霧峰區",
            "大雅區",
            "石岡區",
            "烏日區",
            "大肚區",
            "后里區",
            "神岡區",
            "潭子區",
            "清水區",
            "沙鹿區",
            "北區",
            "南區",
            "西區",
            "北屯區",
            "南屯區",
            "東區",
            "西屯區",
            "中區",
            "新社區",
            "和平區",
            "外埔區",
            "大安區"
        ),
        "彰化縣" to arrayOf(
            "和美鎮",
            "北斗鎮",
            "彰化市",
            "鹿港鎮",
            "社頭鄉",
            "二水鄉",
            "埤頭鄉",
            "芳苑鄉",
            "溪州鄉",
            "秀水鄉",
            "花壇鄉",
            "芬園鄉",
            "大村鄉",
            "埔心鄉",
            "永靖鄉",
            "溪湖鎮",
            "田中鎮",
            "二林鎮",
            "福興鄉",
            "員林市",
            "田尾鄉",
            "線西鄉",
            "伸港鄉",
            "埔鹽鄉",
            "竹塘鄉",
            "大城鄉"
        ),
        "南投縣" to arrayOf(
            "南投市",
            "埔里鎮",
            "草屯鎮",
            "仁愛鄉",
            "竹山鎮",
            "名間鄉",
            "國姓鄉",
            "水里鄉",
            "魚池鄉",
            "集集鎮",
            "中寮鄉",
            "鹿谷鄉",
            "信義鄉"
        ),
        "雲林縣" to arrayOf(
            "斗南鎮",
            "口湖鄉",
            "斗六市",
            "水林鄉",
            "麥寮鄉",
            "東勢鄉",
            "褒忠鄉",
            "元長鄉",
            "四湖鄉",
            "古坑鄉",
            "大埤鄉",
            "莿桐鄉",
            "林內鄉",
            "二崙鄉",
            "崙背鄉",
            "西螺鎮",
            "土庫鎮",
            "北港鎮",
            "虎尾鎮",
            "臺西鄉"
        ),
        "嘉義市" to arrayOf(
            "西區",
            "東區"
        ),
        "嘉義縣" to arrayOf(
            "大林鎮",
            "民雄鄉",
            "溪口鄉",
            "朴子市",
            "水上鄉",
            "中埔鄉",
            "竹崎鄉",
            "梅山鄉",
            "番路鄉",
            "新港鄉",
            "義竹鄉",
            "鹿草鄉",
            "太保市",
            "布袋鎮",
            "東石鄉",
            "六腳鄉",
            "大埔鄉",
            "阿里山鄉"
        ),
        "臺南市" to arrayOf(
            "中西區",
            "東區",
            "歸仁區",
            "東山區",
            "下營區",
            "六甲區",
            "官田區",
            "大內區",
            "西港區",
            "新市區",
            "新化區",
            "善化區",
            "學甲區",
            "柳營區",
            "後壁區",
            "白河區",
            "麻豆區",
            "佳里區",
            "永康區",
            "新營區",
            "鹽水區",
            "安南區",
            "北區",
            "關廟區",
            "山上區",
            "玉井區",
            "南區",
            "左鎮區",
            "仁德區",
            "安平區",
            "將軍區",
            "楠西區",
            "北門區",
            "安定區",
            "七股區",
            "南化區",
            "龍崎區"
        ),
        "高雄市" to arrayOf(
            "彌陀區",
            "內門區",
            "梓官區",
            "甲仙區",
            "岡山區",
            "小港區",
            "鳳山區",
            "前鎮區",
            "旗津區",
            "鳥松區",
            "燕巢區",
            "阿蓮區",
            "茄萣區",
            "林園區",
            "大寮區",
            "大樹區",
            "仁武區",
            "旗山區",
            "美濃區",
            "三民區",
            "新興區",
            "楠梓區",
            "苓雅區",
            "左營區",
            "鹽埕區",
            "鼓山區",
            "前金區",
            "路竹區",
            "杉林區",
            "湖內區",
            "橋頭區",
            "六龜區",
            "大社區",
            "田寮區",
            "永安區",
            "茂林區",
            "桃源區",
            "那瑪夏區"
        ),
        "屏東縣" to arrayOf(
            "屏東市",
            "新園鄉",
            "林邊鄉",
            "南州鄉",
            "佳冬鄉",
            "車城鄉",
            "萬丹鄉",
            "長治鄉",
            "麟洛鄉",
            "里港鄉",
            "鹽埔鄉",
            "高樹鄉",
            "內埔鄉",
            "枋寮鄉",
            "潮州鎮",
            "東港鎮",
            "恆春鎮",
            "枋山鄉",
            "琉球鄉",
            "春日鄉",
            "九如鄉",
            "萬巒鄉",
            "新埤鄉",
            "竹田鄉",
            "崁頂鄉",
            "滿州鄉",
            "三地門鄉",
            "霧臺鄉",
            "瑪家鄉",
            "泰武鄉",
            "來義鄉",
            "獅子鄉",
            "牡丹鄉"
        ),
        "臺東縣" to arrayOf(
            "臺東市",
            "關山鎮",
            "鹿野鄉",
            "太麻里鄉",
            "成功鎮",
            "長濱鄉",
            "卑南鄉",
            "大武鄉",
            "東河鄉",
            "池上鄉",
            "綠島鄉",
            "延平鄉",
            "海端鄉",
            "達仁鄉",
            "金峰鄉",
            "蘭嶼鄉"
        ),
        "花蓮縣" to arrayOf(
            "玉里鎮",
            "新城鄉",
            "吉安鄉",
            "花蓮市",
            "鳳林鎮",
            "瑞穗鄉",
            "壽豐鄉",
            "光復鄉",
            "富里鄉",
            "秀林鄉",
            "萬榮鄉",
            "卓溪鄉",
            "豐濱鄉"
        ),
        "宜蘭縣" to arrayOf(
            "羅東鎮",
            "宜蘭市",
            "員山鄉",
            "冬山鄉",
            "蘇澳鎮",
            "頭城鎮",
            "五結鄉",
            "礁溪鄉",
            "三星鄉",
            "壯圍鄉",
            "南澳鄉",
            "大同鄉"
        ),
        "澎湖縣" to arrayOf(
            "西嶼鄉",
            "馬公市",
            "湖西鄉",
            "白沙鄉",
            "望安鄉",
            "七美鄉"
        ),
        "金門縣" to arrayOf(
            "金城鎮",
            "金沙鎮",
            "金湖鎮",
            "金寧鄉",
            "烈嶼鄉"
        ),
        "連江縣" to arrayOf(
            "北竿鄉",
            "莒光鄉",
            "東引鄉",
            "南竿鄉"
        ),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCsCreateOrderBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        initView()
        return binding.root
    }

    private fun initView() {
        setSpinnerOnclick()
        setTimeOnclick()
        setOnclick()
    }

    private fun setSpinnerOnclick() {
        //  縣市&鄉鎮連動的spinner
        val countyAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, countyList)
        countyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spnCsCreateOrderCounty.adapter = countyAdapter
        binding.spnCsCreateOrderCounty.onItemSelectedListener =
            object : OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val order = viewModel.order.value
                    order?.areaCity = countyList[position]
                    viewModel.order.value = order
                    val districtArray = districtMap[countyList[position]]
                    val districtAdapter = ArrayAdapter(
                        requireContext(),
                        android.R.layout.simple_spinner_item,
                        districtArray!!
                    )
                    districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    binding.spnCsCreateOrderDistrict.adapter = districtAdapter
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // 不執行任何操作
                }
            }
    }

    private fun setTimeOnclick() {
        with(binding) {
            //  選擇日期
            llDatePicker.setOnClickListener {
                // calendar要宣告為此區域的變數，這樣每次點擊按鈕跳出的預選日期方能維持當下日期；
                // 如果宣告在此區域外，下方程式calendar加一個月，會影響日期挑選器預選日期
                val calendar = Calendar.getInstance()
                val datePickerDialog =
                    DatePickerDialog(
                        requireContext(),
                        { _, year, month, day ->
                            // 一月的值是0而非1，所以「month + 1」後才顯示
                            val order = viewModel?.order?.value
                            order?.dateOrdered = "$year-${pad(month + 1)}-${pad(day)}"
                            viewModel?.order?.value = order
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                    )
                // 取得DatePicker物件方可設定可選取的日期區間
                val datePicker = datePickerDialog.datePicker
                // 設定可選取的開始日為今日
                datePicker.minDate = calendar.timeInMillis
                // 設定可選取的結束日為一個月後
                calendar.add(Calendar.MONTH, 1)
                datePicker.maxDate = calendar.timeInMillis
                // 最後要呼叫show()方能顯示
                datePickerDialog.show()
            }

            //  選擇時間
            llCsCreateOrderTimeBegin.setOnClickListener {
                val calendar = Calendar.getInstance()
                TimePickerDialog(
                    requireContext(),
                    { _, hour, minute ->
                        val order = viewModel?.order?.value
                        order?.timeOrderedStart = "${pad(hour)}:${pad(minute)}"
                        viewModel?.order?.value = order
                    },
                    calendar.get(Calendar.HOUR),
                    calendar.get(Calendar.MINUTE),
                    false
                ).show()
            }

            llCsCreateOrderTimeEnd.setOnClickListener {
                val calendar = Calendar.getInstance()
                TimePickerDialog(
                    requireContext(),
                    { _, hour, minute ->
                        val order = viewModel?.order?.value
                        order?.timeOrderedEnd = "${pad(hour)}:${pad(minute)}"
                        viewModel?.order?.value = order
                    },
                    calendar.get(Calendar.HOUR),
                    calendar.get(Calendar.MINUTE),
                    false
                ).show()
            }
        }
    }

    private fun setOnclick() {
        with(binding) {
            //  拍照
            llCsCreateOrderPics.setOnClickListener {
                if (viewModel?.photo?.value?.photo3 == null) {
                    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    takePictureSmallLauncher.launch(intent)
                }
            }

            // 選取優惠券
            llCoupon.setOnClickListener {
                val originPrice = edtTxtCost.text.toString()
                if (originPrice.isNotEmpty() && originPrice.toInt() == 0) {
                    Toast.makeText(
                        context,
                        getString(R.string.toast_csCreateOrder_keyInCost),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    saveCreateOrderInfo()
                    findNavController().navigate(R.id.action_csCreateOrderFragment_to_csCouponPickerFragment)
                }
            }

            //  跳轉下一頁
            btnCsCreateOrderNext.setOnClickListener {
                viewModel?.order?.value?.let {
                    saveCreateOrderInfo()
                    val bundle = Bundle()
                    bundle.putSerializable("order", it)
                    bundle.putSerializable("photos", viewModel?.photo?.value)
                    findNavController().navigate(
                        R.id.action_csCreateOrderFragment_to_csOrderConfirmedFragment,
                        bundle
                    )
                }
            }
        }
    }

    override fun onResume() {
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        super.onResume()
        Navigation.findNavController(
            requireActivity(),
            R.id.customer_nav_host_fragment
        ).currentBackStackEntry?.savedStateHandle?.getLiveData<Coupon>("coupon")
            ?.observe(viewLifecycleOwner) { coupon ->
                val order = viewModel.order.value
                if (viewModel.order.value?.originalPrice!! < coupon.minPrice) {
                    Toast.makeText(
                        context,
                        getString(R.string.toast_csCreateOrder_notMeetMinCost),
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (coupon.discountType) {
                    viewModel.coupon.value = coupon
                    order?.couponDiscount = coupon.discountMoney
                } else {
                    viewModel.coupon.value = coupon
                    val discount = coupon.discount
                    val cost = binding.edtTxtCost.text.toString().toDouble()
                    val calculatedValue = ((1 - discount) * cost).roundToInt()
                    order?.couponDiscount = calculatedValue
                }
                viewModel.order.value = order
            }
    }

    private fun pad(number: Int): String {
        return if (number >= 10) {
            number.toString()
        } else {
            "0$number"
        }
    }

    private var takePictureSmallLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.extras?.let { bundle ->
                    val picture = bundle["data"] as Bitmap?
                    picture?.let {
                        viewModel.addCapturedPhoto(it)
                    }
                }
            }
        }

    private fun saveCreateOrderInfo() {
        with(binding) {
            val order = viewModel?.order?.value
            order?.let {
                it.areaDistrict =
                    districtMap[viewModel?.order?.value?.areaCity]?.get(spnCsCreateOrderDistrict.selectedItemPosition)
                        ?: ""
                it.livingRoomSize = edtTxtCsCreateOrderLivingroomSize.text.toString().toInt()
                it.kitchenSize = edtTxtCsCreateOrderKitchenSize.text.toString().toInt()
                it.bathRoomSize = edtTxtCsCreateOrderBathroomSize.text.toString().toInt()
                it.roomSize = edtTxtCsCreateOrderBathroomSize.text.toString().toInt()
                it.originalPrice = edtTxtCost.text.toString().toInt()
            }
            viewModel?.order?.value = order
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
    }
}