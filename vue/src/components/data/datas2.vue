 <template>
  <el-card>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/datas' }">实时状态</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <!-- 卡片视图 -->
      <!-- 列表 -->
      <!-- :cell-class-name="cellClassName" -->
      <vxe-grid resizable
                form-config
                show-overflow
                export-config
                border="inner"
                ref="xGrid"
                height="700"
                row-id="id"
                :sort-config="{trigger: 'cell', defaultSort: {field: 'state', order: 'desc'}}"
                :filter-config="{remote: true}"
                :pager-config="tablePage"
                :toolbar="tableToolbar"
                :columns="tableColumn"
                :cell-class-name="cellClassName"
                :proxy-config="tableProxy"
                :radio-config="{labelField: 'id', reserve: true, highlight: true}">
        <!--自定义插槽 name_default-->
        <template v-slot:name_default="{ row }">
          <span>
            <!-- <span>{{ row.state }}</span> -->
            <span>{{getStateData(row.state )}}</span>
          </span>
        </template>

        <template v-slot:toolbar_buttons>
          <vxe-form :data="findData"
                    @submit="searchEvent"
                    @reset="searchEvent">
            <vxe-form-item title="负责人"
                           field="user">
              <vxe-input v-model="findData.user"
                         clearable
                         placeholder="负责人"></vxe-input>
            </vxe-form-item>
            <vxe-form-item title="服务名称"
                           field="service">
              <vxe-input v-model="findData.service"
                         clearable
                         placeholder="服务名称"></vxe-input>
            </vxe-form-item>
            <vxe-form-item title="服务IP"
                           field="IP">
              <vxe-input v-model="findData.ip"
                         clearable
                         placeholder="服务IP"></vxe-input>
            </vxe-form-item>
            <vxe-form-item :item-render="{ name: '$buttons', children: [{ props: { type: 'submit', content: '查询', status: 'primary' } }, { props: { type: 'reset', content: '重置' } }] }">
            </vxe-form-item>

          </vxe-form>
        </template>

        <template v-slot:empty>
          <span style="color: red;">
            <img src="static/other/img1.gif">
            <p>没有更多数据了！</p>
          </span>
        </template>
      </vxe-grid>

    </el-card>
  </el-card>
</template>
<script>
export default {
  data () {
    return {
      findData: {
        service: null,
        ip: null,
        user: null
      },
      tablePage: {
        pageSize: 15,
        pageSizes: [5, 15, 20, 50, 100, 200, 500, 1000]
      },
      tableProxy: {
        seq: true, // 启用动态序号代理
        // sort: true, // 启用排序代理
        filter: true, // 启用筛选代理
        ajax: {
          // 任何支持 Promise API 的库都可以对接（fetch、jquery、axios、xe-ajax）
          query: ({ page, sort, filters }) => {
            // 处理排序条件
            const queryParams = Object.assign({
              sort: sort.property,
              order: sort.order
            }, this.formData)
            // 处理筛选条件
            filters.forEach(({ column, property, values }) => {
              queryParams[property] = values.join(',')
            })
            return this.findList(page, queryParams)
          }
        }
      },
      tableToolbar: {
        export: true,
        custom: true,
        slots: {
          buttons: 'toolbar_buttons'
        }
      },
      tableColumn: [
        // { type: 'seq', width: 60, fixed: 'left' },
        // { type: 'radio', title: 'ID', width: 120, fixed: 'left' },
        // { field: 'state', title: '状态', minWidth: 10, remoteSort: true },
        { field: 'state', title: '测试状态', slots: { default: 'name_default' } },
        { field: 'user', title: '负责人', minWidth: 100, remoteSort: true },
        { field: 'service', title: '服务名称', minWidth: 200, remoteSort: true },
        { field: 'ip', title: '服务IP', minWidth: 200, remoteSort: true },
        { field: 'url', title: '服务检查API', minWidth: 200, remoteSort: true },
        { field: 'createdate', title: '触发时间', width: 160, remoteSort: true, formatter: this.formatDate }

      ]
    }
  },
  methods: {

    cellClassName ({ row, rowIndex, column, columnIndex }) {
      if (column.property === 'state') {
        return 'row-green' + row.state
      }
    },
    formatDate ({ cellValue }) {
      return this.$XEUtils.toDateString(cellValue, 'yyyy-MM-dd HH:ss:mm')
    },
    async findList (page, queryParams) {
      const { data: ras } = await this.$http.post('/service-health-monitoring-log/queryListPage',
        {
          page: {
            current: page.currentPage,
            size: page.pageSize,
            desc: [
              'state'
            ]
          },
          where: {
            user: this.findData.user === '' ? null : this.findData.user,
            service: this.findData.service === '' ? null : this.findData.service,
            ip: this.findData.ip === '' ? null : this.findData.ip
          }
        })
      return { result: ras.body.records, page: { pageSize: ras.body.size, currentPage: ras.body.current, total: ras.body.total } }
    },
    searchEvent () {
      this.$refs.xGrid.commitProxy('reload')
    },
    getStateData (data) {
      // var st = ''
      //  1 成功 2 down 3 访问异常 4 发送通知异常
      //  1 down 2 访问异常 3 发送通知异常  4 成功
      switch (data) {
        case 1:
          return 'DOWN'
        case 2:
          return '访问异常'
        case 3:
          return '发送通知异常'
        case 4:
          return '成功'
        default:
          return '未知'
      }
    }

  }
}

</script>
 <style lang="less" scoped>
</style>
