[?php use_stylesheets_for_form($form) ?]
[?php use_javascripts_for_form($form) ?]

<div class="sf_admin_filter">
  [?php if ($form->hasGlobalErrors()): ?]
    [?php echo $form->renderGlobalErrors() ?]
  [?php endif; ?]

  <form action="[?php echo url_for('<?php echo $this->getUrlForAction('collection') ?>', array('action' => 'filter')) ?]" method="post">
    <table cellspacing="0" width=100% >
      <tbody>
        [?php $field_count=0; $cell_num=4;?]	  
        [?php foreach ($configuration->getFormFilterFields($form) as $name => $field): ?]		
        [?php if ((isset($form[$name]) && $form[$name]->isHidden()) || (!isset($form[$name]) && $field->isReal())) continue ?]
		[?php $field_count++;?]
		[?php if($field_count==1 || $field_count%$cell_num==1): ?]
		<tr class="[?php echo $class ?]" >
		[?php endif; ?]		
          [?php include_partial('<?php echo $this->getModuleName() ?>/filters_field', array(
            'name'       => $name,
            'attributes' => $field->getConfig('attributes', array()),
            'label'      => $field->getConfig('label'),
            'help'       => $field->getConfig('help'),
            'form'       => $form,
            'field'      => $field,
            'class'      => 'sf_admin_form_row sf_admin_'.strtolower($field->getType()).' sf_admin_filter_field_'.$name,
          )) ?]
		[?php if($field_count%$cell_num==0):?]
		</tr>
		[?php endif; ?]	 
        [?php endforeach; ?]
		
		[?php $mod_field=$field_count%$cell_num; ?]
		[?php $null_field=($cell_num-$mod_field)*2; ?]
		[?php //echo $null_field; ?]
		[?php //echo $mod_field; ?]
		[?php //echo $field_count; ?]
		[?php //echo $cell_num; ?]
		[?php if ($mod_field!=0):?]
		[?php for ($i=1;$i<=$null_field;$i++): ?]
		<td></td>
		[?php endfor; ?]
		</tr>
		[?php endif; ?]	
      </tbody>

      <tfoot>
        <tr>
          <td colspan="[?php echo $cell_num*2; ?]">
            [?php echo $form->renderHiddenFields() ?]
            [?php echo link_to(__('Reset', array(), 'sf_admin'), '<?php echo $this->getUrlForAction('collection') ?>', array('action' => 'filter'), array('query_string' => '_reset', 'method' => 'post')) ?]
            <input type="submit" value="[?php echo __('Filter', array(), 'sf_admin') ?]" />
          </td>
        </tr>
      </tfoot>	
	  
    </table>
  </form>
</div>
