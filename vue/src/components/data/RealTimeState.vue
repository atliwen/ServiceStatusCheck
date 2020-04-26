<template>
  <el-card>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/test' }">图形数据</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 查询 -->
    <el-card>
      <vxe-form :data="findData"
                @submit="getDatalist">
        <vxe-form-item title="负责人"
                       field="user">
          <vxe-input v-model="findData.user"
                     clearable
                     placeholder="负责人"></vxe-input>
        </vxe-form-item>
        <vxe-form-item title="服务名称"
                       clearable
                       field="service">
          <vxe-input v-model="findData.service"
                     placeholder="服务名称"></vxe-input>
        </vxe-form-item>
        <vxe-form-item title="服务IP"
                       clearable
                       field="ip">
          <vxe-input v-model="findData.ip"
                     placeholder="服务IP"></vxe-input>
        </vxe-form-item>

        <vxe-form-item title="时间段"
                       clearable
                       field="">
          <el-date-picker v-model="findData.fDate"
                          type="datetimerange"
                          :picker-options="pickerOptions"
                          range-separator="至"
                          start-placeholder="开始日期"
                          end-placeholder="结束日期"
                          align="right">
          </el-date-picker>
        </vxe-form-item>
        <vxe-form-item>
          <vxe-button type="submit"
                      status="primary">查询</vxe-button>
        </vxe-form-item>
      </vxe-form>

    </el-card>

    <!-- 图形 -->
    <el-card>
      <div id="chart1"></div>
    </el-card>

    <!-- 对话框 -->
    <el-dialog title="详细信息"
               :visible.sync="dialogFormVisible"
               width="45%">
      <el-form :model="showData">
        <el-form-item label="服务名称 ："
                      :label-width="formLabelWidth">
          <el-tag>{{showData.service}}</el-tag>
        </el-form-item>
        <el-form-item label="ip ："
                      :label-width="formLabelWidth">
          <el-tag>{{showData.ip}}</el-tag>
        </el-form-item>
        <el-form-item label="服务检查API ："
                      :label-width="formLabelWidth">
          <el-tag>{{showData.url}}</el-tag>
        </el-form-item>
        <el-form-item label="负责人 ："
                      :label-width="formLabelWidth">
          <el-tag>{{showData.user}}</el-tag>
        </el-form-item>

        <el-form-item label="请求时长 ："
                      :label-width="formLabelWidth">
          <el-tag>{{showData.calltime}}</el-tag>
        </el-form-item>

        <el-form-item label="状态 ："
                      :label-width="formLabelWidth">
          <el-tooltip class="item"
                      effect="dark"
                      content="1 成功  ， 2 down ， 3 访问异常 ， 4 发送通知异常"
                      placement="top">
            <el-tag>{{showData.state}}</el-tag>
          </el-tooltip>
        </el-form-item>
        <el-form-item label="触发时间 ："
                      :label-width="formLabelWidth">
          <el-date-picker v-model="showData.createdate"
                          type="datetime"
                          value-format="timestamp"
                          placeholder="选择日期时间">
          </el-date-picker>
        </el-form-item>

        <el-form-item label="响应消息 ："
                      :label-width="formLabelWidth">
          <el-input type="textarea"
                    autosize
                    placeholder="请输入内容"
                    v-model="showData.data">
          </el-input>

        </el-form-item>
      </el-form>
    </el-dialog>

  </el-card>
</template>
<script>
export default {
  name: 'HelloWorld',
  data () {
    return {
      pickerOptions: {
        shortcuts: [
          {
            text: '最近一小时',
            onClick (picker) {
              const end = new Date()
              const start = new Date()
              start.setTime(start.getTime() - 3600 * 1000)
              picker.$emit('pick', [start, end])
            }
          },
          {
            text: '最近一天',
            onClick (picker) {
              const end = new Date()
              const start = new Date()
              start.setTime(start.getTime() - 3600 * 1000 * 24)
              picker.$emit('pick', [start, end])
            }
          }, {
            text: '最近一周',
            onClick (picker) {
              const end = new Date()
              const start = new Date()
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7)
              picker.$emit('pick', [start, end])
            }
          }, {
            text: '最近一个月',
            onClick (picker) {
              const end = new Date()
              const start = new Date()
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30)
              picker.$emit('pick', [start, end])
            }
          }, {
            text: '最近三个月',
            onClick (picker) {
              const end = new Date()
              const start = new Date()
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90)
              picker.$emit('pick', [start, end])
            }
          }]
      },
      value2: '',
      newDate: '',
      showData: {
        id: 1,
        service: '测试服务',
        ip: '10.10.12.22',
        notice: 'http://baidu.com.cn',
        user: '李文',
        update: null,
        createdate: 1587094398000,
        state: 4,
        calltime: -2342,
        data: "{a:'a'}"
      },
      findData: {
        user: '',
        service: '',
        ip: '',
        fDate: [new Date().setTime(new Date().getTime() - 3600 * 1000), new Date()]
      },
      dialogFormVisible: false,
      formLabelWidth: '120px',
      datas: []
    }
  },
  methods: {
    dateToString: function (date) {
      var year = date.getFullYear()
      var month = (date.getMonth() + 1).toString()
      var day = (date.getDate()).toString()
      if (month.length === 1) {
        month = '0' + month
      }
      if (day.length === 1) {
        day = '0' + day
      }
      var dateTime = year + '-' + month + '-' + day
      return dateTime
    },
    onclict: function (params) {
      this.showData = params.data
      this.dialogFormVisible = true
    },
    async getDatalist () {
      if (this.findData.fDate === null) {
        alert('时间不能为空')
        return
      }
      const { data: ras } = await this.$http.post('/service-health-monitoring-log/getLeGeList', {
        user: this.findData.user,
        service: this.findData.service,
        ip: this.findData.ip,
        le: this.findData.fDate[1],
        ge: this.findData.fDate[0]
      })
      this.datas = ras
      this.options(ras.body)
    },
    options: function (data) {
      var right = []
      var error = []
      var jsonData = JSON.parse(JSON.stringify(data))
      jsonData.forEach(function (officer, index, array) {
        if (officer.state > 3) {
          right.push(officer)
        } else {
          error.push(officer)
        }
      })
      var option = {
        backgroundColor: '#eee',
        legend: {
          data: ['正常', '异常'],
          left: 10
        },
        title: {
          left: 'center',
          text: '服务名称'
        },
        brush: {
          toolbox: ['rect', 'polygon', 'lineX', 'lineY', 'keep', 'clear'],
          xAxisIndex: 0
        },
        toolbox: {
          feature: {
            magicType: {
              type: ['stack', 'tiled']
            },
            dataZoom: {
              yAxisIndex: 'none'
            },
            dataView: {}
          }
        },
        tooltip: {
          formatter: function (params, ticket, callback) {
            var newDate = new Date()
            newDate.setTime(params.data.createdate)
            var st = ''
            //  1 成功 2 down 3 访问异常 4 发送通知异常
            //  1 down 2 访问异常 3 发送通知异常  4 成功
            switch (params.data.state) {
              case 1:
                st = 'DOWN'
                break
              case 2:
                st = '访问异常'
                break
              case 3:
                st = '发送通知异常'
                break
              case 4:
                st = '成功'
                break
              default:
                st = '未知'
                break
            }
            return ' </br>' + ' 服务名称 : ' + params.data.service +
              ' </br>' + '   iP : ' + params.data.ip +
              ' </br>' + ' 服务检查API : ' + params.data.url +
              ' </br>' + ' 状态 : ' + st +
              ' </br>' + ' 负责人 : ' + params.data.user +
              ' </br>' + ' 耗时 : ' + params.data.calltime +
              ' </br>' + ' 时间 : ' + newDate.toLocaleString('chinese', { hour12: false }).replace('24:', '00')
          }
        },
        dataZoom: [{
          type: 'inside',
          start: 0,
          end: 100
        }, {
          start: 0,
          end: 10,
          handleSize: '80%',
          handleStyle: {
            color: '#fff',
            shadowBlur: 3,
            shadowColor: 'rgba(0, 0, 0, 0.6)',
            shadowOffsetX: 2,
            shadowOffsetY: 2
          }
        }],
        xAxis: {
          type: 'time',
          // data: xAxisData,
          // name: '',
          axisLine: { onZero: true },
          splitLine: { show: false },
          splitArea: { show: false }
        },
        yAxis: {
          inverse: true,
          splitArea: { show: false }
        },
        grid: {
          left: 100
        },
        dataset: [{
          // 序号为 0 的 dataset。
          source: right
        }, {
          // 序号为 1 的 dataset。
          source: error
        }],
        series: [
          {
            name: '异常',
            type: 'bar',
            stack: 'one',
            encode: { x: 6, y: 8 },
            datasetIndex: 1

          },
          {
            name: '正常',
            type: 'bar',
            stack: 'one',
            encode: { x: 6, y: 8 },
            datasetIndex: 0

          }
        ]
      }
      this.chart = this.$echarts.init(document.getElementById('chart1'))
      this.chart.clear()
      this.chart.setOption(option)
      this.chart.on('click', this.onclict)
    }
  },
  mounted () {
    this.getDatalist()
  }

}
</script>

<style scoped>
#chart1 {
  width: 100;
  height: 800px;
}
</style>
