<template>
  <el-card>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/test' }">实时状态</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <div id="chart1"></div>

    </el-card>

    <el-card>
      <!-- Table -->
      <el-button type="text"
                 @click="dialogTableVisible = true">打开嵌套表格的 Dialog</el-button>

      <el-dialog title="收货地址"
                 :visible.sync="dialogTableVisible">
        <el-table :data="gridData">
          <el-table-column property="date"
                           label="日期"
                           width="150"></el-table-column>
          <el-table-column property="name"
                           label="姓名"
                           width="200"></el-table-column>
          <el-table-column property="address"
                           label="地址"></el-table-column>
        </el-table>
      </el-dialog>
    </el-card>
  </el-card>
</template>
<script>
export default {
  name: 'HelloWorld',
  data () {
    return {
      gridData: [{
        date: '2016-05-02',
        value: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-04',
        value: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-01',
        value: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-03',
        value: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }],
      dialogTableVisible: false,
      datas: []
    }
  },
  methods: {
    onclict: function (params) {
      console.log('object :', params)
      this.dialogTableVisible = true
    },
    async getDatalist () {
      const { data: ras } = await this.$http.get('/service-health-monitoring-log/queryList')

      console.log('Datalist011 :', this.ras)
      this.datas = ras
      this.options(ras.body)
      // this.datas = ras.body
      // this.total = ras.body.total
    },
    options: function (data) {
      var right = []
      var error = []
      var jsonData = JSON.parse(JSON.stringify(data))
      jsonData.forEach(function (officer, index, array) {
        console.log(officer) // {id: 20, name: "Captain Piett"},{id: 24, name: "General Veers"},{id: 56, name: "Admiral Ozzel"},{id: 88, name: "Commander Jerjerrod"}
        if (officer.state > 1) {
          right.push(officer)
        } else {
          error.push(officer)
        }
      })
      // var a = [{
      //   id: 1,
      //   service: '测试服务',
      //   ip: '10.10.12.22',
      //   notice: 'http://baidu.com.cn',
      //   user: '李文',
      //   update: null,
      //   createdate: 1587094398000,
      //   state: 4,
      //   calltime: -2342,
      //   data: "{a:'a'}"
      // },
      // {
      //   id: 2,
      //   service: '测试服务',
      //   ip: '10.10.12.22',
      //   notice: 'http://baidu.com.cn',
      //   user: '李文',
      //   update: null,
      //   createdate: 1587094338000,
      //   state: 4,
      //   calltime: 306,
      //   data: "{a:'a'}"
      // }]
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
            switch (params.data.state) {
              case 1:
                st = '成功'
                break
              case 2:
                st = 'down'
                break
              case 3:
                st = '访问异常'
                break
              case 4:
                st = '发送通知异常'
                break
              default:
                st = '未知'
                break
            }

            return ' </br>' + '   iP : ' + params.data.ip +
              ' </br>' + ' 状态 : ' + st +
              ' </br>' + ' 用户 : ' + params.data.user +
              ' </br>' + ' 耗时 : ' + params.data.calltime +
              ' </br>' + ' 时间 : ' + newDate.toLocaleString()
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
            name: '正常',
            type: 'bar',
            stack: 'one',
            encode: { x: 6, y: 8 },
            datasetIndex: 0

          },
          {
            name: '异常',
            type: 'bar',
            stack: 'one',
            encode: { x: 6, y: 8 },
            datasetIndex: 1

          }
        ]
      }

      this.chart = this.$echarts.init(document.getElementById('chart1'))
      this.chart.clear()
      this.chart.setOption(option)

      this.chart.on('click', this.onclict)
      // debugger

      // option.dataset.source = jsonData
      // this.chart.setOption(option)
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
