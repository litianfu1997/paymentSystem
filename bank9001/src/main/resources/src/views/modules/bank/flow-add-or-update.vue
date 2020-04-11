<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="金额" prop="fMoney">
      <el-input v-model="dataForm.fMoney" placeholder="金额"></el-input>
    </el-form-item>
    <el-form-item label="类型" prop="fType">
      <el-input v-model="dataForm.fType" placeholder="类型"></el-input>
    </el-form-item>
    <el-form-item label="账户外键" prop="aId">
      <el-input v-model="dataForm.aId" placeholder="账户外键"></el-input>
    </el-form-item>
    <el-form-item label="流水创建时间" prop="fDate">
      <el-input v-model="dataForm.fDate" placeholder="流水创建时间"></el-input>
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
          fId: 0,
          fMoney: '',
          fType: '',
          aId: '',
          fDate: ''
        },
        dataRule: {
          fMoney: [
            { required: true, message: '金额不能为空', trigger: 'blur' }
          ],
          fType: [
            { required: true, message: '类型不能为空', trigger: 'blur' }
          ],
          aId: [
            { required: true, message: '账户外键不能为空', trigger: 'blur' }
          ],
          fDate: [
            { required: true, message: '流水创建时间不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.fId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.fId) {
            this.$http({
              url: this.$http.adornUrl(`/ware/flow/info/${this.dataForm.fId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.fMoney = data.flow.fMoney
                this.dataForm.fType = data.flow.fType
                this.dataForm.aId = data.flow.aId
                this.dataForm.fDate = data.flow.fDate
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
              url: this.$http.adornUrl(`/ware/flow/${!this.dataForm.fId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'fId': this.dataForm.fId || undefined,
                'fMoney': this.dataForm.fMoney,
                'fType': this.dataForm.fType,
                'aId': this.dataForm.aId,
                'fDate': this.dataForm.fDate
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
