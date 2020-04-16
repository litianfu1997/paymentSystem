<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="订单唯一凭证" prop="orderAccount">
      <el-input v-model="dataForm.orderAccount" placeholder="订单唯一凭证"></el-input>
    </el-form-item>
    <el-form-item label="流水号" prop="flowId">
      <el-input v-model="dataForm.flowId" placeholder="流水号"></el-input>
    </el-form-item>
    <el-form-item label="订单详细" prop="desc">
      <el-input v-model="dataForm.desc" placeholder="订单详细"></el-input>
    </el-form-item>
    <el-form-item label="账户id" prop="aId">
      <el-input v-model="dataForm.aId" placeholder="账户id"></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          orderId: 0,
          orderAccount: '',
          flowId: '',
          desc: '',
          aId: ''
        },
        dataRule: {
          orderAccount: [
            { required: true, message: '订单唯一凭证不能为空', trigger: 'blur' }
          ],
          flowId: [
            { required: true, message: '流水号不能为空', trigger: 'blur' }
          ],
          desc: [
            { required: true, message: '订单详细不能为空', trigger: 'blur' }
          ],
          aId: [
            { required: true, message: '账户id不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.orderId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.orderId) {
            this.$http({
              url: this.$http.adornUrl(`/payment/order/info/${this.dataForm.orderId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.orderAccount = data.order.orderAccount
                this.dataForm.flowId = data.order.flowId
                this.dataForm.desc = data.order.desc
                this.dataForm.aId = data.order.aId
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/payment/order/${!this.dataForm.orderId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'orderId': this.dataForm.orderId || undefined,
                'orderAccount': this.dataForm.orderAccount,
                'flowId': this.dataForm.flowId,
                'desc': this.dataForm.desc,
                'aId': this.dataForm.aId
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
