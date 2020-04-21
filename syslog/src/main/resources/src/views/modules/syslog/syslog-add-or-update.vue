<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="操作用户" prop="username">
      <el-input v-model="dataForm.username" placeholder="操作用户"></el-input>
    </el-form-item>
    <el-form-item label="操作" prop="operation">
      <el-input v-model="dataForm.operation" placeholder="操作"></el-input>
    </el-form-item>
    <el-form-item label="执行方法" prop="method">
      <el-input v-model="dataForm.method" placeholder="执行方法"></el-input>
    </el-form-item>
    <el-form-item label="参数" prop="prams">
      <el-input v-model="dataForm.prams" placeholder="参数"></el-input>
    </el-form-item>
    <el-form-item label="执行时长" prop="operationTime">
      <el-input v-model="dataForm.operationTime" placeholder="执行时长"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>
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
          id: 0,
          username: '',
          operation: '',
          method: '',
          prams: '',
          operationTime: '',
          createTime: ''
        },
        dataRule: {
          username: [
            { required: true, message: '操作用户不能为空', trigger: 'blur' }
          ],
          operation: [
            { required: true, message: '操作不能为空', trigger: 'blur' }
          ],
          method: [
            { required: true, message: '执行方法不能为空', trigger: 'blur' }
          ],
          prams: [
            { required: true, message: '参数不能为空', trigger: 'blur' }
          ],
          operationTime: [
            { required: true, message: '执行时长不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/syslog/syslog/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.username = data.syslog.username
                this.dataForm.operation = data.syslog.operation
                this.dataForm.method = data.syslog.method
                this.dataForm.prams = data.syslog.prams
                this.dataForm.operationTime = data.syslog.operationTime
                this.dataForm.createTime = data.syslog.createTime
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
              url: this.$http.adornUrl(`/syslog/syslog/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'username': this.dataForm.username,
                'operation': this.dataForm.operation,
                'method': this.dataForm.method,
                'prams': this.dataForm.prams,
                'operationTime': this.dataForm.operationTime,
                'createTime': this.dataForm.createTime
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
