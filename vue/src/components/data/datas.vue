 <template>
  <el-card>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/datas' }">服务配置</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <!-- 卡片视图 -->
      <el-row>
        <vxe-form :data="findData"
                  @submit="getDatalist">
          <vxe-form-item title="负责人"
                         field="user">
            <vxe-input v-model="findData.user"
                       clearable
                       placeholder="负责人"></vxe-input>
          </vxe-form-item>
          <vxe-form-item title="服务名称"
                         field="state">
            <vxe-input v-model="findData.service"
                       clearable
                       placeholder="服务名称"></vxe-input>
          </vxe-form-item>
          <vxe-form-item title="服务IP"
                         field="state">
            <vxe-input v-model="findData.ip"
                       clearable
                       placeholder="服务IP"></vxe-input>
          </vxe-form-item>

          <vxe-form-item>
            <vxe-button type="submit"
                        status="primary">查询</vxe-button>
          </vxe-form-item>
          <vxe-form-item>
            <vxe-button icon="fa fa-plus"
                        @click="insertEvent()">新增</vxe-button>
          </vxe-form-item>
        </vxe-form>
      </el-row>
      <!-- 列表 -->
      <vxe-table border
                 row-key
                 resizable
                 :data="datas"
                 :edit-config="{trigger: 'click', mode: 'cell'}"
                 @edit-closed="editClosedEvent">
        <vxe-table-column type="seq"
                          field="id"
                          width="30"></vxe-table-column>
        <vxe-table-column field="service"
                          title="服务名称"
                          :edit-render="{name: 'input', attrs: {type: 'text'}}"></vxe-table-column>
        <vxe-table-column field="ip"
                          title="IP"
                          :edit-render="{name: 'input', attrs: {type: 'text'}}"></vxe-table-column>
        <vxe-table-column field="url"
                          title="服务检查API地址"
                          :edit-render="{name: 'input', attrs: {type: 'text'}}"></vxe-table-column>
        <vxe-table-column field="user"
                          title="负责人"
                          :edit-render="{name: 'input', attrs: {type: 'text'}}"></vxe-table-column>
        <vxe-table-column field="notice"
                          title="通知地址"
                          :edit-render="{name: 'input', attrs: {type: 'text'}}"></vxe-table-column>
        <vxe-table-column title="操作"
                          width="100">
          <template v-slot="{ row }">
            <!-- <vxe-button type="text"
                        icon="fa fa-trash-o"
                        @click="deleteEvent(row)"></vxe-button> -->
            <vxe-button icon="fa fa-trash-o vxe-danger-color"
                        @click="deleteConfirm(row)">删除</vxe-button>
          </template>
        </vxe-table-column>
      </vxe-table>

      <!-- 分页区域 -->
      <el-pagination @size-change="handleSizeChange"
                     @current-change="handleCurrentChange"
                     :current-page="current"
                     :page-sizes="[5, 10, 50, 100]"
                     :page-size="size"
                     layout="total, sizes, prev, pager, next, jumper"
                     :total="total">
      </el-pagination>

      <vxe-modal v-model="showEdit"
                 :title="selectRow ? '编辑&保存' : '新增&保存'"
                 width="800"
                 :loading="submitLoading"
                 resize
                 destroy-on-close>
        <vxe-form :data="findData"
                  :items="formItems"
                  title-align="right"
                  title-width="100"
                  @submit="submitEvent"></vxe-form>
      </vxe-modal>

    </el-card>
  </el-card>
</template>
<script>
export default {
  data () {
    return {
      newStatus: 2,
      selectRow: null,
      showEdit: false,
      submitLoading: false,
      datas: [],
      // 总页数
      total: 0,
      // 每页调速
      size: 5,
      // 当前第几页
      current: 1,
      // 搜索关键字
      characteristic: null,
      findData: {
        id: null,
        service: null,
        ip: null,
        notice: null,
        user: null,
        url: null
      },
      formData: {
        service: null,
        ip: null,
        notice: null,
        user: null,
        url: null
      },
      formItems: [
        { field: 'service', title: '服务名称', span: 12, itemRender: { name: '$input', props: { placeholder: '请输入服务名称' } } },
        { field: 'ip', title: 'IP', span: 12, itemRender: { name: '$input', props: { placeholder: '请输入服务IP' } } },
        { field: 'url', title: 'url', span: 12, itemRender: { name: '$input', props: { placeholder: '请输入服务检查URL地址' } } },
        { field: 'user', title: '负责人', span: 12, itemRender: { name: '$input', props: { placeholder: '请输入负责人' } } },
        { field: 'notice', title: '通知地址', span: 12, itemRender: { name: '$input', props: { placeholder: '请输入通知地址' } } },
        { align: 'center', span: 24, titleAlign: 'left', itemRender: { name: '$buttons', children: [{ props: { type: 'submit', content: '提交', status: 'primary' } }, { props: { type: 'reset', content: '重置' } }] } }
      ]
    }
  },
  created () {
    this.getDatalist()
  },
  methods: {
    deleteConfirm (row) {
      this.$XModal.confirm('您确定要删除该数据?').then(type => {
        if (type === 'confirm') {
          this.deleteEvent(row)
        }
      })
    },
    async deleteEvent (row) {
      const { data: ras } = await this.$http.post('service-health-monitoring/delete',
        {
          id: row.id
        })
      if (ras.status !== '0000') {
        alert('删除失败')
      } else {
        this.getDatalist()
      }
    },
    async  submitEvent () {
      const { data: ras } = await this.$http.post('service-health-monitoring/add',
        {
          service: this.findData.service === '' ? null : this.findData.service,
          ip: this.findData.ip === '' ? null : this.findData.ip,
          user: this.findData.user === '' ? null : this.findData.user,
          url: this.findData.url === '' ? null : this.findData.url,
          notice: this.findData.notice === '' ? null : this.findData.notice
        })
      if (ras.status !== '0000') {
        alert('新增失败')
      } else {
        this.getDatalist()
        this.showEdit = false
      }
    },
    insertEvent () {
      this.selectRow = null
      this.showEdit = true
    },

    async getDatalist () {
      const { data: ras } = await this.$http.post('service-health-monitoring/queryPage',
        {
          page: {
            current: this.current,
            size: this.size
          },
          where: {
            service: this.findData.service === '' ? null : this.findData.service,
            ip: this.findData.ip === '' ? null : this.findData.ip,
            user: this.findData.user === '' ? null : this.findData.user
          }
        })
      this.datas = ras.body.records
      this.total = ras.body.total
    },
    handleSizeChange (newSize) {
      this.size = newSize
      this.current = 1
      this.getDatalist()
    },
    handleCurrentChange (newCurrent) {
      this.current = newCurrent
      this.getDatalist()
    },
    async editClosedEvent ({ row, column }, event) {
      await this.$http.post('/service-health-monitoring/update',
        {
          id: row.id, [column.property]: row[column.property]
        })
      this.getDatalist()
    },
    cellDBLClickEvent ({ row }) {

    }

  }

}
</script>
 <style lang="less" scoped>
</style>
